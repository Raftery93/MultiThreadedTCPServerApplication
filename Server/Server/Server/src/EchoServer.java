import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EchoServer {
	public static void main(String[] args) throws Exception {
		ServerSocket m_ServerSocket = new ServerSocket(2004, 10);
		int id = 0;
		while (true) {
			Socket clientSocket = m_ServerSocket.accept();
			ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
			cliThread.start();
		}
	}
}

class ClientServiceThread extends Thread {
	Socket clientSocket;
	String message;
	int clientID = -1;
	boolean running = true;
	ObjectOutputStream out;
	ObjectInputStream in;

	ClientServiceThread(Socket s, int i) {
		clientSocket = s;
		clientID = i;
	}

	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void run() {
		System.out.println(
				"Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("Accepted Client : ID - " + clientID + " : Address - "
					+ clientSocket.getInetAddress().getHostName());

			do {
				try {

					sendMessage("Press 1 for Signing up\n Press 2 for Logging in \nPress 3 to exit");
					message = (String) in.readObject();

					if (message.compareToIgnoreCase("1") == 0) {

						System.out.println("User wishes to Sign Up");

						sendMessage("Please enter a Username");

						String usernameS = (String) in.readObject();

						sendMessage("Please enter a Password");

						String passwordS = (String) in.readObject();

						if (usernameS != null && passwordS != null) {

							File usersFile = new File("users.txt");
							// file = "users.txt";

							if (!usersFile.exists()) {
								FileWriter fileWriter = new FileWriter("users.txt");
								PrintWriter printWriter = new PrintWriter(fileWriter);
								printWriter.printf("%s %s\n", usernameS, passwordS);
								printWriter.close();
								System.out.println("User writting to file successfully\n");
							} else {
								// PrintWriter printWriter = new PrintWriter(fileWriter);
								PrintWriter printWriter = new PrintWriter(
										new BufferedWriter(new FileWriter("users.txt", true)));
								printWriter.printf("%s %s\n", usernameS, passwordS);
								printWriter.close();
								System.out.println("User writting to file successfully\n");
							}

							System.out.println("User has signed up");
							sendMessage("Sign up successful\n" + "Username: " + usernameS + "\n" + "Password: "
									+ passwordS + "\n");

							sendMessage("Please enter your name\n");
							String name = (String) in.readObject();

							sendMessage("Please enter your address\n");
							String address = (String) in.readObject();

							sendMessage("Please enter your PPSNumber\n");
							String PPSNumber = (String) in.readObject();

							sendMessage("Please enter your age\n");
							String age = (String) in.readObject();

							sendMessage("Please enter your weight\n");
							String weight = (String) in.readObject();

							sendMessage("Please enter your height\n");
							String height = (String) in.readObject();

							User user = new User(name, address, PPSNumber, age, weight, height);

							File userDetailsFile = new File("users.txt");// changed from users.txt

							if (!userDetailsFile.exists()) {
								FileWriter fileWriter = new FileWriter("userDetails.txt");
								PrintWriter printWriter = new PrintWriter(fileWriter);
								printWriter.printf("%s %s %s %s %s %s\n", name, address, PPSNumber, age, weight,
										height);
								printWriter.close();
								System.out.println("User details writting to file successfully\n");
							} else {
								// PrintWriter printWriter = new PrintWriter(fileWriter);
								PrintWriter printWriter = new PrintWriter(
										new BufferedWriter(new FileWriter("userDetails.txt", true)));
								printWriter.printf("%s %s %s %s %s %s\n", name, address, PPSNumber, age, weight,
										height);
								printWriter.close();
								System.out.println("User details writting to file successfully\n");
							}

							sendMessage("Your details:\n" + user + "\nPlease Log in with your details");

						} else {
							System.out.println("Sign up unsuccesful");
							sendMessage("Sign up was unsuccessful\n" + "Please enter a valid username and password!");
						}
					}

					else if (message.compareToIgnoreCase("2") == 0) {

						int login = 0;

						System.out.println("User wishes to log in");

						// ----------
						while (login == 0) {
							sendMessage("Please enter a Username");
							// String string1 = (String)in.readObject();
							String usernameL = (String) in.readObject();

							// sendMessage("Please enter a string");
							sendMessage("Please enter a Password");
							// String string2 = (String)in.readObject();
							String passwordL = (String) in.readObject();

							if (usernameL != null && passwordL != null) {
								System.out.println("User has logged in");
								sendMessage("Log in successful\n" + "Username: " + usernameL + "\n" + "Password: "
										+ passwordL);

								File file = new File("users.txt");
								File file1 = new File("userDetails.txt");

								Scanner input = new Scanner(file);
								Scanner input1 = new Scanner(file1);

								int i = -1;

								while (input.hasNext()) {

									String userN = input.next();
									String passN = input.next();
									i++;

									// sendMessage("txtUser: ." + userN + ". txtPass: ." + passN +
									// ".\nU: ." + usernameL + ". P: ." + passwordL + ".");
									// sendMessage("User: " + usernameL + " txtPass: " + passwordL);

									if (usernameL.equals(userN) && passwordL.equals(passN)) {
										// if(userN == usernameL && passN == passwordL) {
										login = 1;
										System.out.println("Success with loggin in");
										sendMessage("Checking user " + i
												+ " Login details: Logged in\nYour Basic Details:");

										// Number of ints per line:
										int width = 6;

										// This will be the output - a list of rows, each with 'width' entries:
										ArrayList<String[]> results = new ArrayList<String[]>();

										String line = null;

										while (input1.hasNextLine()) {

											String[] row = new String[width];

											// For each number..
											for (int j = 0; j < width; j++) {

												// Read the number and add it to the current row:
												row[j] = input1.next();

											}
											results.add(row);

											// Go to the next line (optional, but helps deal with erroneous input
											// files):
											if (input1.hasNextLine()) {

												// Go to the next line:
												input1.nextLine();

											}

										}
										input1.close();

										for (int j = 0; j < width; j++) {
											sendMessage(results.get(i)[j]);
											System.out.println(results.get(i)[j]);
										}

										// load in userdetails at index 'i'

										// i=-1;
									} else {
										if (login != 1) {
											if (login != 2) {
												sendMessage("Checking user " + i
														+ " Login details: Invalid username / password");
												login = 2;
											}

										}
									}

								}

							} else {
								System.out.println("Log in unsuccesful");
								sendMessage(
										"Log in was unsuccessful\n" + "Please enter a valid username and password!");
							}

						} // maybe do while?

						// ----------

						// ----------

						// ----------

						if (login == 1) {
							// place block below into this so if they fail the login it does not continue

							sendMessage("Press 1 for Add/View/Delete Fitness Records\n"
									+ "Press 2 for Add/View/Delete Meal Records\n");
							message = (String) in.readObject();

							if (message.equalsIgnoreCase("1")) {
								sendMessage("1)Add\n2)Delete\n3)View(Last 10)");
								message = (String) in.readObject();
								int a = Integer.parseInt(message);

								if (message.equalsIgnoreCase("1")) {
									sendMessage("Please enter your username for verification:\n");
									String username = (String) in.readObject();

									sendMessage("Please enter number:\n1)For Walking\n2)For Running\n3)For Cycling\n");
									String mode = (String) in.readObject();

									sendMessage("Please enter the duration\n");
									String duration = (String) in.readObject();

									FitnessRecord fitnessRecord = new FitnessRecord(mode, duration);

									File fitnessRecordsFile = new File("users.txt");

									if (!fitnessRecordsFile.exists()) {

										String filename = username + "FitnessRecords.txt";

										FileWriter fileWriter = new FileWriter(filename);
										PrintWriter printWriter = new PrintWriter(fileWriter);
										printWriter.printf("%s %s\n", mode, duration);
										printWriter.close();
										System.out.println("User fitness records writting to file successfully\n");
									} else {
										String filename = username + "FitnessRecords.txt";
										// PrintWriter printWriter = new PrintWriter(fileWriter);
										PrintWriter printWriter = new PrintWriter(
												new BufferedWriter(new FileWriter(filename, true)));
										printWriter.printf("%s %s\n", mode, duration);
										printWriter.close();
										System.out.println("User fitness records writting to file successfully\n");
									}
									sendMessage("Fitness record added, details:\n" + fitnessRecord);

								}
								// here
								else if (message.equalsIgnoreCase("3")) {

									sendMessage("Please enter your username for verification:\n");
									String username = (String) in.readObject();

									int width = 2;

									File file2 = new File(username + "FitnessRecords.txt");

									Scanner input3 = new Scanner(file2);

									ArrayList<String[]> outputFitness = new ArrayList<String[]>();

									String line = null;

									while (input3.hasNextLine()) {

										String[] row = new String[width];

										// For each number..
										for (int j = 0; j < width; j++) {

											// Read the number and add it to the current row:
											row[j] = input3.next();

										}
										outputFitness.add(row);

										// Go to the next line (optional, but helps deal with erroneous input files):
										if (input3.hasNextLine()) {

											// Go to the next line:
											input3.nextLine();

										}

									}
									input3.close();

									for (int i = 0; i < 4; i++) {
										for (int j = 0; j < width; j++) {
											sendMessage(outputFitness.get(i)[j]);
											System.out.println(outputFitness.get(i)[j]);
										}
									}

								}

							}

						}

					}

				} catch (ClassNotFoundException classnot) {
					System.err.println("Data received in unknown format");
				}

			} while (!message.equals("3"));

			System.out.println(
					"Ending Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
