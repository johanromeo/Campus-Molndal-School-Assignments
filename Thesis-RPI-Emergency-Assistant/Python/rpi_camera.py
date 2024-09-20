from picamera2 import Picamera2
from picamera2.encoders import H264Encoder
from picamera2.outputs import FfmpegOutput
import time

class Cam:
    """Class is responsible for configuring and providing methods for video recording and taking still images."""
    
    def __init__(self, audio_device:str):
        """Initializes Cam with the chosen audio device being used for recording video with sound.
        
        Params:
            audio_device (str): The name of the audio input device in your system.
        """
    
        self.picam2 = Picamera2()
        self.encoder = H264Encoder(10000000)
        self.audio_device = audio_device
        
    def video_configurations(self, output_filename:str) -> None:
        """Configuring basic video settings. Only used for method: record_video_with_sound().
        
        Params:
            output_filename (str): The chosen name for the video recorded.
        """
        
        self.video_config = self.picam2.create_video_configuration()
        self.picam2.configure(self.video_config)
        self.output = FfmpegOutput(output_filename=output_filename, audio=True, audio_device=self.audio_device)
        
    
    def record_video_with_sound(self, recording_time:int, output_filename:str) -> None:
        """Record a video with sound. Video duration is based on the value of recording_time argument in seconds.
        
        Params:
            recording_time (int): The number of seconds of the video.
            output_filename (str): The chosen name for the video recorded.
        """
        
        self.video_configurations(output_filename)
        self.picam2.start_recording(self.encoder, self.output)
        time.sleep(recording_time)
        self.picam2.stop_recording()
        self.picam2.stop()
        
        
    def take_image(self, output_filename:str) -> None:
        """Switch from video mode and take a still image.
        
        Params:
            output_filename (str): The chosen name for the image taken.
        """
        
        self.picam2.start()
        self.picam2.switch_mode_and_capture_file('still', output_filename)
        self.picam2.stop()
