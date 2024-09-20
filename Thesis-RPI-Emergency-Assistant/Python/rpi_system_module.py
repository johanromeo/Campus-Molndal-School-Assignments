import os
import sys
import shutil

ROOT_DIR_PATH = r'/home/pi/rpi-emergency-assistant/'


def restart_script() -> None:
    """If an exception is causing the script to crash, then restart it."""
       
    os.execv(sys.executable, ['python3'] + sys.argv)    
    

def get_absolute_path_of_file(file_extension:str) -> str:
    """Loop over files in root directory and return the wanted file path based on the file extension(.jpg or .mp4).
    
    Params:
        file_extension (str): The file's extension(.jpg or .mp4).

    Returns:
        str: The absolut path of a file found in the projects root directory.
    """

    wanted_file = ''
    
    for file in os.listdir(ROOT_DIR_PATH):
        if file.endswith(file_extension):
            wanted_file = os.path.join(ROOT_DIR_PATH, file)
    return wanted_file

def get_filename(file_extension:str) -> str:
    """Loop over files in the project's root directory and return the wanted file name based on the file extension(.jpg or .mp4).
    
    Params:
        file_extension (str): The file's extension(.jpg or .mp4).

    Returns:
        str: The name of the file.
    """
    
    wanted_file = ''
    
    for file in os.listdir(ROOT_DIR_PATH):
        if file.endswith(file_extension):
            wanted_file = file
    return wanted_file

        
def move_file(filename:str) -> None:
    """Moves a file to its corresponding directory - /images or /videos - based on the filename.
    
    Params:
        filename (str): The name of the file you want to move.
    
    """
    
    if filename.endswith('.jpg'):
        move_image = '/home/pi/rpi-emergency-assistant/file_outputs/images'
        shutil.move(filename, move_image)
    elif filename.endswith('.mp4'):
        move_video = '/home/pi/rpi-emergency-assistant/file_outputs/videos'
        shutil.move(filename, move_video)
        
        
    
        

