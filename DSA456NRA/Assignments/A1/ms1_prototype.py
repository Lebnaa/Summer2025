


""""
this is for the data cleaning
"""
def standardize_birth_date_format(date_str):
    """
    Accepts a date string in mixed format.
    Returns a standardized date string in dd-Mon-yyyy format.
    """
    pass

def clean_athlete_bio_data(data):
    """
    Cleans athlete bio data:
    - Standardizes birth date formats
    - Fills missing height/weight with 'N/A'
    """
    pass

""""
this is for the data merging
"""

def is_duplicate_athlete(existing_athletes, new_athlete):
    """
    Checks if the new athlete already exists in the dataset.
    Uses name, country, and birth date to determine duplication.
    """
    pass

def merge_paris_athletes(original_data, paris_data):
    """
    Merges Paris athlete data into the original dataset after duplicate check.
    Assigns new athlete IDs where needed.
    """
    pass


"""
this for utility functions
"""
def generate_new_athlete_id():
    """
    Generates a new unique athlete ID.
    """
    pass

def read_csv_file(filepath):
    """
    Reads and returns data from a CSV file.
    """
    pass

def write_csv_file(filepath, data, headers):
    """
    Writes data to a CSV file with given headers.
    """
    pass


""""
this is for output generation
"""
def create_new_olympic_athlete_bio():
    """
    Creates a new_olympic_athlete_bio.csv file from the original data.
    """
    pass

def create_new_olympic_athlete_event_results():
    """
    Creates new_olympic_athlete_event_results.csv with age column added to header.
    Adds empty age values to each row.
    """
    pass

def create_new_olympics_country():
    """
    Creates new_olympics_country.csv. Can be a copy of original.
    """
    pass

def create_new_olympics_games():
    """
    Creates new_olympics_games.csv. Can be a copy of original.
    """
    pass

def create_new_medal_tally():
    """
    Creates new_medal_tally.csv with only header:
    edition, edition_id, Country, NOC, number_of_athletes, gold_medal_count, silver_medal_count, bronze_medal_count, total_medals
    """
    pass

