# CountryFlag Application

## Assignment details
- Generate a Single Activity App named CountryFlags
- The project will be to simply to show flag images when searching using ISO 3166-1 alpha-2 (2 letter) codes, e.g. IE for Ireland or CN for China
- There are 5 simple user requirements for this app:
  - As a user, I should able to launch the app and enter a 2 letter country code to retrieve the relevant country flag (64px size)
  - As a user, if I enter an invalid or unavailable country code,  I should get an error dialog
  - As a user, if I enter a valid country code, I should be able to save the country code into a list of country codes  if the flag preview is available.
  - As a user, I should be able to see the list of saved country codes with the country flag beside it.
  - As a user I should be able to search the Saved Country list using country name or country code from the saved list.
>> You can use https://www.countryflags.io/ for fetching country flags using country code, there are instructions on the site for retrieving any flags there.
 

## Features Implemented
- MVVM
- Data Binding
- Coroutines
- Clean Architecture

## Libraries used
- Coroutines - for multi threading
- Databinding and view model for MVVM
- Picaso for image Loading and getting error callback if image not exists
- Junit and mockito for Unit testing
- DIffUtils to make recyclerview adapter load items effeciently when refresh country


## How to Run the application
- 
- Click on the CountryFlagAPp in device dashboard
- Type country code to search any country
- Minimum length of country code is 2
- If the country code is not exist, user will see an error dialog, the save button will be disabled
- WHen User click on ok of the alert dialog, the search text will be empty
- On Each save, the recycleview will be updated with the new data
- Added filted features to search the Coutries whicha re saved


## Here is the screen shot and Gif image for the application
<table>
<tr>
<td>
  <img src="https://github.com/spdobest/CountryFlag/blob/main/app/images/ss1.png" width="200" height="350" />
 </td>
<td>
 <img src="https://github.com/spdobest/CountryFlag/blob/main/app/images/ss2.png" width="200" height="350"/> 
</td>
  <td>
  <img src="https://github.com/spdobest/CountryFlag/blob/main/app/images/ss3.png" width="200" height="350" />
 </td>
<td>
 <img src="https://github.com/spdobest/CountryFlag/blob/main/app/images/ss4.png" width="200" height="350"/> 
</td>
  <td>
  <img src="https://github.com/spdobest/CountryFlag/blob/main/app/images/ss5.png" width="200" height="350" />
 </td>
</tr>
</table> 

## GIF image
<img src="https://github.com/spdobest/CountryFlag/blob/main/app/images/gifImage.gif" width="300" height="500" />
