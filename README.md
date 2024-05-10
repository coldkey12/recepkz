# RECEPKZ
This is a food recipe web application.
## Instruments used: 
Java JDK 20.0.1 -
springframework.boot 3.2.5 -
Lombok -
postgresql -
jpa -
thymeleaf -
bootstrap v5.2 -
Gradle 
## Prerequisites

You will need the following tools:

- Git
- Java JDK
- Gradle

## Installation

1. **Clone the Repository**: First, you need to clone the repository to your local machine or server where you want to run the application. You can do this using the command `git clone https://github.com/coldkey12/recepkz.git`.

2. **Navigate to the Project Directory**: Change your current working directory to the project directory. You can do this using the command `cd recepkz`.

## Usage

1. **Build the Project**: You can build the project using Gradle. If you have Gradle installed, you can use the command `gradle build`. If you are using the Gradle wrapper included in the project, you can use `./gradlew build` on Unix-based systems or `gradlew.bat build` on Windows.

2. **Run the Application**: After building the project, you can run the application using `java -jar build/libs/recepkz-0.0.1-SNAPSHOT.jar`.

3. **Access the Application**: Once the application is running, you can access it via the appropriate port on your local machine or server. For example, if it's a web application running on Spring Boot's default port, you might access it at `http://localhost:8080/home`.

## Notes
It would be much easier to initialize this application if you just use IntellijIDEA (if you have one), you need to open project through IDEA and open gradle.build file and nothing else. 
I finished this web application in two days, so I'm aware of many things that should be done the other way. API was not included due to many difficulties and changes it would require to be done in the project.

I started building my project by setting up the model package (setting up DB at the same time). After that I started off with writing controllers -> service -> repositories -> html.
I do it in this exact order everytime I write a web application, so there is no running through the code trying to figure out what you we're actually doing. There are problems with accessing the pages you're not supposed to, for example, you can easily drop entire application by doing localhost:8080/update-recipe/(any id) when you're in guest mode. This can be easily avoided by adding a security configuration file (Spring security), however implementing this feature would require more time then I have. Also, there are no pictures because for some reason they never display whatever I do, I surfed many websites and stackoverflow cases, I asked my programming school teachers and other students, nobody helped, even that one indian guy couldn't solve the problem... Also, HTML needs a lot of rework (renaming ids etc.). But overall, if you're not trying to break the app and just use buttons, it will work perfectly.

## Levels
Lvl 1 - +
Lvl 2 - +
Lvl 3 - (This project was made just in 2 days so I had little time to think of it.)
