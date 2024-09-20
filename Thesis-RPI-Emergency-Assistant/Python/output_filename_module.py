import datetime

def generate_output_filename(prefix:str) -> str:        
    """Method for generating correct output_filename based on the required prefix.
    
    Params:
        prefix (str): The prefix for the filename to be generated.

    Raises:
        TypeError: If prefix is empty.
        TypeError: If prefix is not set to 'video' or 'image'.

    Returns:
        str: A formatted string containing the prefix, todays date and time and the extension.
    """
        
    output_extension = ''
    try:
        if prefix.lower() == 'video':
            output_extension = '.mp4'
        elif prefix.lower() == 'image':
            output_extension = '.jpg'
        elif prefix == '':
            raise TypeError('Error: Prefix cannot be empty.')
        else:
            # TypeError raised to prevent encoder problems later on
            raise TypeError('Error: Prefix must either be set to video or image.')
    # Catch, print and make client understand what's wrong
    except TypeError as e:
        print(f'Error occurred in generate_output_filename: {e}')
        return None

    # Use of date and time to get a unique and relevant filename
    get_current_date_time = datetime.datetime.now()
    formatted_date_time = get_current_date_time.strftime('%Y-%m-%d-%H:%M:%S')
    return f'{prefix}_{formatted_date_time}{output_extension}'

    