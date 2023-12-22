# Project README

## Project Overview

This service reads card status data from CSV files located in a specific location on a daily schedule and saves the data into a database. It also provides APIs that allow users to query card status details using their card number or mobile number.

### Controller

#### CardStatusTrackerController

- **Summary:** APIs for card delivery-related operations
- **Description:** REST APIs to perform card status operations, such as reading status from CSV files.


### Services

#### CardStatusTrackerServices

- **Summary:** Services for card status operations
- **Description:** Contains services related to card status operations, including scheduled tasks to read data from CSV files and save it to the database.


### Getting Started

1. Set environment variables or change values in 
bash ```application.properties```
2. Update the Jib plugin configuration in the `pom.xml` with your desired image name.
3. Build the Docker image using:
```mvn compile jib:dockerBuild```.
4. In the Docker Compose file, change the image name to the new image name.
5. Run ```docker-compose up``` to start the application in a Docker container.

#### If not using Docker:

1. Change the path in `application.properties` to the complete path of your source file.
2. Run `mvn clean install` to generate the JAR file in the `target` folder.
3. Execute `java -jar filename.jar` to run the application.

**Note:** The application will read and save data every day at 10 AM.

### Additional Notes

- To copy a CSV file from the local machine to the Docker container, use the following command:
  ```
  docker cp /path/to/your/local/file $(docker-compose ps -q yourContainerName):/path/in/container
  ```
- The application reads and saves data from CSV files every day at 10 AM.

Feel free to explore and customize the application to fit your specific requirements!
