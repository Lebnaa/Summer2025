# Milestone 1 Problem Identification


# What unknown wrong data is there?#

### olympic_athlete_bio:
- **born Column**: Mixed formats (e.g., 24 November 1873, 1879, 04-Apr-49)
- **Height/Weight Columns**: Empty fields (e.g., ,,,)

### olympic_athlete_bio.csv
- **Medal Column Inconsistencies**:
  - Some entries are blank (e.g., ,,)
  - Others contain values (Gold/Silver/Bronze)

### olympics_country.csv:
- Includes obsolete entries:
  - FRG, WEST Germany (no longer exist)
  - Note: These are historically correct but may need handling for current projects
- Special case:
  - UNK - UNKNOWN (no country specified)

### olympics_games.csv:
- Contains missing or incomplete dates



# How will wrong/unknown data be handled?#

If the data is a mandatory field, we will remove the field since we cannot authenticate the data.
If the data is a non-essential field, we will add placeholder values like "Unknown" or "N/A".

We can identify and remove duplicates with a search and remove function.

For mixed formats (e.g., inconsistent date formats in the born column), we will standardize the data into a uniform format like dd-Mon-yyyy.



# How will Paris data be organized? How does this relate to the original data file? how will you determine the duplicate athlete entries?

From the Paris folder, we have athletes, events and other files we need to merge to the original data file. 
Only new athletes will be added to the file. This will be checked using name, country, date of birth to ensure that there are not duplicates. If there is no record of the athlete, then we will also generate an athlete ID for the individual.

All the event and country additions will be appended to the original file after verification.



# How will you be able to tell if your application was working? Are there specfic records that you can check?

