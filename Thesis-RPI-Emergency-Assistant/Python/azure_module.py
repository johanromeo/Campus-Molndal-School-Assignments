import os
from azure.iot.device import IoTHubDeviceClient
from azure.core.exceptions import AzureError
from azure.storage.blob import BlobClient

class BlobService:
    """Class is responsible for uploading a file from an IoT device to Blob Storage.
    
        Legal:
             This is Microsoft Azure example code, which has been remade into a class with some modifications to fit the program's need.
             Docstrings are only written where modifications are made by the author, the rest is being left as it was originally made.
             The original example code and explanations are found here: https://learn.microsoft.com/en-us/azure/iot-hub/file-upload-python
    """

    
    def __init__(self, connection_string:str):
        """Initlializes the class with the primary connection string provided by IoT Hub.
        
        Params:
            connection_string (str): The connection string used in API calls which allows the device to communicate with IoT Hub.
        """
        
        self.connection_string = connection_string
        self.device_client = IoTHubDeviceClient.create_from_connection_string(connection_string)
        
    
    def store_blob(self, blob_info, file_name):
    
        try:
            sas_url = "https://{}/{}/{}{}".format(
                blob_info["hostName"],
                blob_info["containerName"],
                blob_info["blobName"],
                blob_info["sasToken"]
            )

            print("\nUploading file: {} to Azure Storage as blob: {} in container {}\n".format(file_name, blob_info["blobName"], blob_info["containerName"]))

            # Upload the specified file
            with BlobClient.from_blob_url(sas_url) as blob_client:
                with open(file_name, "rb") as f:
                    result = blob_client.upload_blob(f, overwrite=True)
                    return (True, result)

        except FileNotFoundError as ex:
            # catch file not found and add an HTTP status code to return in notification to IoT Hub
            ex.status_code = 404
            return (False, ex)

        except AzureError as ex:
            # catch Azure errors that might result from the upload operation
            return (False, ex)
        
    def run_sample(self, device_client, path_to_file:str):
        """Connect the client and upload the file.
        
        Params:
            (Modification) path_to_file (str): Absolute path to the file that's being sent to blob storage.
        """
        
        # Connect the client
        device_client.connect()

        # Get the storage info for the blob
        blob_name = os.path.basename(path_to_file)
        storage_info = device_client.get_storage_info_for_blob(blob_name)

        # Upload to blob
        success, result = self.store_blob(storage_info, path_to_file)

        if success == True:
            print("Upload succeeded!")

            device_client.notify_blob_upload_status(
                storage_info["correlationId"], True, 200, "OK: {}".format(path_to_file)
            )

        else:
            # If the upload was not successful, the result is the exception object
            print("Upload failed. Exception is: \n") 
            print(result)
            print()

            device_client.notify_blob_upload_status(
                storage_info["correlationId"], False, result.status_code, str(result)
            )
        
            
    # Method name and structure changed from 'main()' to better fit the program's need
    def upload_blob(self, path_to_file:str) -> None:
        """Method for abstracting away the run_sample() implementation.
        
        Params:
            path_to_file (str): The absolute path to the file needed by method: run_sample() in order to send file.
            
        Raises:
            Exception: Printing error message to the console if problems occur.
        """
        
        try:
            self.run_sample(self.device_client, path_to_file)
        except Exception as e:
            print(f'Error: {e}')