from pocketsphinx import LiveSpeech

class Mic:
    """A class representing a microphone for keyphrase detection."""

    
    def __init__(self, keyphrase:str):
        """Initializes Mic with the chosen keyphrase to be used. The keyphrase intended for this program is 'help' only, as of now.

        Params:
            keyphrase (str): The word the microphone is listening to.
        """
        self.keyphrase = keyphrase
        
    
    def listen_for_keyphrase(self) -> bool:
        """The microphone is listening for the chosen keyphrase. If caught, it prints out the keyphrase to the console.
        
        Raises:
            Exception: If any errors occurs during speech recognition.
            
        Returns:
            bool: Returns True if the keyphrase is caught.
        """
        
        try:
            # kws_threshold used for keyphrase detection accuracy
            speech = LiveSpeech(keyphrase=self.keyphrase, kws_threshold=1e-20)
            for phrase in speech:
                print(f'{phrase} keyphrase detected!')
                # If keyphrase is detected, return True to let script in '__main__' start
                return True
        except Exception as e:
            print(f'Error occured in listen_for_keyphrase: {e}')
            
                        
    