This project was written by Conor Raftery (G00274094) as an Operating Systems Assignment.

This project is unfinished, details outlined below:

- Login working.
- Signup working.
- Add fitness record working.
- View fitness record working (but buggy).

To run the program start the server initially, (or visit the already running server at 35.189.218.131). After this is done, run the client provided.
For demonstration purposes, I have hard-coded the IP address into the client. This server can be accessed simultaneously by multiple users. It also
only provides service to valid users.
For test purposes, some of these user include:
-Username-Password-
Test	 Testtt

Con 	 4321

Sample	 sPass


Or alternitavely you can create your own user(signup)


-The signup is done by adding a username and password (pairs) to a user.txt file. All users are stored in this. The new user is then prompted to
 add basic details to his/her profile (name, age etc...). This is done by using a seperate class and uses an arrayList. These details are then
 added to a seperate file called userDetails.txt

-The login asks the user to verify a username and password (must match) to continue into the program. If there is a match made, the user is displayed
 there basic details from userDetails.txt, this is done by knowing the index of which the user logged in at (in user.txt), and displays the corresponding
 index in the userDetails.txt

-Add fitness record is done by just prompting the user for the details, and outputting them into their own .txt file, denoted by *their_username*FitnessRecords.txt

-View fitness records displays 2 of the fitness records for that user (bug).