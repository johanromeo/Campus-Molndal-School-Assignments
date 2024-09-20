from rpi_camera import Cam
from rpi_microphone import Mic
from azure_module import BlobService

import output_filename_module as filename
import rpi_system_module as rsm
import time

# Variables needed for objects
audio_device = 'hw:2,0'
keyphrase = 'help'
connection_string = 'xxxxx-xxxxx-xxxxx'

# Constants needed for cam and mic methods
IMAGE_FILE_PREFIX = 'image'
IMAGE_FILE_EXTENSION = '.jpg'

VIDEO_FILE_PREFIX = 'video'
VIDEO_FILE_EXTENSION = '.mp4'

# Initialie objects
cam = Cam(audio_device)
mic = Mic(keyphrase)
blob_service = BlobService(connection_string)


def main():
    """Main function to initialize the script."""
    
    while True:
        # Reset filenames
        video_filename = ''
        image_filename = ''
        try:
            # Listening for the keyphrase
            if mic.listen_for_keyphrase():
                # Generate unique filenames
                video_filename = filename.generate_output_filename(VIDEO_FILE_PREFIX)
                image_filename = filename.generate_output_filename(IMAGE_FILE_PREFIX)
                
                # Record, upload and store name of the video recording
                cam.record_video_with_sound(1, video_filename)
                blob_service.upload_blob(rsm.get_absolute_path_of_file(VIDEO_FILE_EXTENSION))
                saved_video = rsm.get_filename(VIDEO_FILE_EXTENSION)
                
                # Take picture, upload and store the name of the image
                cam.take_image(image_filename)
                blob_service.upload_blob(rsm.get_absolute_path_of_file(IMAGE_FILE_EXTENSION))
                saved_image = rsm.get_filename(IMAGE_FILE_EXTENSION)
                
                # Move video and image to their corresponding directories so root directory is kept clean and to not interfere with next set of video and image
                rsm.move_file(saved_video)
                rsm.move_file(saved_image)
                
        # Catch unwanted exceptions that's interferring with the script
        except Exception as e:
            print(f'Error occured in main script: {e.with_traceback()}\nRestarting script in 3 seconds...')
            time.sleep(3)
            # Close Azure IoT resources before restarting script
            blob_service.device_client.shutdown()
            # Restarting the script
            rsm.restart_script()
                            
            
if __name__ == '__main__':
    main()
    