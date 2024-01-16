//import all the things that will be needed in this program
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
/**
This program is a memory game that aims to teach the user more about the negative impacts of technology and how it can affect them
@author Altaz Punja
@version 1.0
@since 2023-05-12
*/
class Main {
  /**
  This method is used to randomize the order of an integer array
  @param a This is the array to randomize
  @param b This is the length of the array to randomize
  */
  public static void randomArr(int [] a, int b){
    //if the array has element left to randomize, there's nothing else to randomize
    if (b==1){
      return;
    }
    //Get a number within the indices of the array
    int rIn = (int) (Math.random() * b);
    //set a temporary variable to last value of the array
    int temp = a[b-1];
    //make the last element equal to the randomaly chosen element
    a[b-1]=a[rIn];
    //set the random element equal to the last one
    a[rIn] = temp;
    //Run the method again but with size left to randomize equal to one less than before
    randomArr(a, b-1);
  }
  /**
  This method is used to randomize the order of an integer array
  @param a This is the array to randomize
  @param b This is the length of the array to randomize
  */
  //THIS METHOD IS THE SAME AS ABOVE SO NO COMMENTS
  public static void randomArr(String [] a, int b){
    if (b==1){
      return;
    }
    int rIn = (int) (Math.random() * b);
    //This variable is a string because array is a string
    String temp = a[b-1];
    a[b-1]=a[rIn];
    a[rIn] = temp;

    randomArr(a, b-1);
  }
  /**
  This method is used to check if a string can be converted to an integer
  @param a This is the string to check
  @return This returns the string changed to an int, or -1 if it can't be
  */
  public static int numCheck(String a){
    //try to convert string to int
    try{
      int r = Integer.parseInt(a);
    //return string converted
      return r;
    }
  //if error occurs when trying to convert return -1
    catch(NumberFormatException e){
      return -1;
    }
  }
  /**
  This method makes the user click e to continue on with the program
  */
  public static void cont(){
    //Scanner for user input
    Scanner input = new Scanner (System.in);
    //while loop runs until user inputs e
    while (true){
      //tell user to input e and get input
      System.out.println("Press e to continue");
      String cont = input.nextLine();
      //if input=e break while loop
      if (cont.trim().equalsIgnoreCase("e")) break;
      //error message if e isn't entered
      System.out.println("Inalid input, please enter the letter e.");
    }
    //clear screen
    cs();
  }
  /**
  This method goes through the data file and returns the highest score
  @return This returns the high score as an int
  */
  public static int highscore(){
    //set high variable to 0
    int high = 0;
    //try has to be used for File IO
    try{
      //Scanner to read through the file
      Scanner fileInput = new Scanner (new File ("data.txt"));
      //while loop runs while there's another line
      while(fileInput.hasNext()){
        //get info on the line and split it up into an array
        String data = fileInput.nextLine();
        String [] info = data.split(",");
        //set user highscore to an integer variable
        int userH = Integer.parseInt(info[2]);
        //if user highscore>current highscore, update value of current high score
        if (userH>high){
          high = userH;
        }
      }
    }
      //catches IO error
    catch (IOException e){
      System.out.println("You're bad");
    }
    //return highscore
    return high;
  }
  /**
  This method clears the screen
  */
  public static void cs(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  /**
  This method prints the value of a 2-d Array
  @param a This is the String 2-d array to print
  */
  public static void pArray(String [][] a){
    //double for loop to go through every element of the array
    for (int r = 0;r<a.length;r++){
      for (int c=0;c<a[r].length;c++){
        //print every element on the row
        System.out.print(a[r][c]+" ");
      }
      //spaces out each row on another line
      System.out.println("");
    }
  }
  /**
  This is the main memory game that tests users memory and gives them info on technology
  @param highscore This is the highscore before the method is run
  @return This returns the users score
  */
  public static int memory(int highscore) throws InterruptedException{
    //Declare most of the variables that will be used and open scanner for user input
      boolean correct = true;
      Scanner input = new Scanner (System.in);
      int lvl = 0;
      int uAns = 0;
      int x=0;
      String learn = "";
    //This array includes all the emojis and information that the user will be shown in the program
                        //Vision problems
      String [] probs = {"👀;Increased screen time has been proven to lead to eye strain and other vision problems.;Symptoms of eye strain include blurry vision, dry eyes, headaches, and discomfort. One of the main side effects of a lot of screen time is nearsightedness, which has been linked to screen time in children and teens. All these problems are caused by your screen time, brightness, and blue light emitted by the screen.\nIt is important to not overdue the amount you are on your devices, take frequent breaks, and stay a comfortable distance away from screens.",
                         
                         //problems sleeping
                         "😴;Sleep deprivation is a common problem associated with technology use, and negatively affects many people.;Screens often emit blue light. This not only leads to vision problems, but makes it a lot harder to fall asleep. This is because blue light interferes with your body's natural sleep cycle and suppresses the production of the sleep hormone, melatonin. Going through social media or playing games before sleep can also make it harder to wind down and fall asleep. Around 60% of adults look at their phone in the last hour before they fall asleep, and this leads to sleeping an average of one hour less than people who stay off their devices. Sleep deprivation is also a common contributor to depression. To prevent this, try staying off your phone before you fall asleep, and establish a consistent routine where you stay off devices.",
                         
                         //problems with posture
                         "🐪;Spending a lot of time on your devices can lead to having bad posture, which in turn leads to many health problems.;Often times when we are on our devices, we look down or lean forward, which leads to strain on your back and neck muscles. This can cause pain, stiffness and soreness in your neck and shoulders. Sitting for long periods of time can also lead to muscle imbalances and a weaker core. This causes slouching, rounding of the shoulders, and more. To prevent this negative effect of technology, take frequent breaks and move around a lot. Also, try to sit or stand straight when on your devices. A nice chair and monitor at eye level are also good for you.",
                         
                         //info on ability to think
                         "🧐;Too much technology has been shown to lead to worse creativity, academic performance, problem-solving ability and more.;Too much time on technology can lead to many mental drawbacks in the way you think. First, technology can link to reduced creativity, because too much time on social media can lead to less time doing things that challenge your creativity. Many people also tend to go on social media whilst studying, and this leads to worse learning and academic performance. When you go on your phone you are not multitasking, instead you are overworking your brain and interrupting your work. This leads to decreased concentration and increased stress. Technology also has a negative effect on your ability to problem solve, as it leads to an overreliance and dependence on technology, and lessens the amount of hands-on experience you can get. Too much screen time has also been linked to worse memory formation, and the ability to think for yourself.",
                         
                         //info on behavioural issues.
                         "😡;Increased technology use can lead to many behavioural problems, such as an aggressive attitude, lack of empathy, and impulsivity.;Being able to say whatever you want to someone on social media without repercussions has led to many people becoming impulsive. We get so used to receiving instant gratification and feedback through technology, that we tend to become more impulsive. This leads to worse decision-making and reckless behaviour. Increased technology use can also lead to a lack of empathy and social skills, and this can make it harder to make friends and develop healthy relationships. To overcome these behavioural problems, you should try having more face-to-face interactions, and engage in activities that promote self-reflection and empathy. Finally, if you ever have a lot of concerns, seeking professional help can only help.",

                         //info on physical well-being
                         "💪;Too much technology can lead to many problems with your well-being as instead of doing physical activities, you tend to stay inside.;Increased screen time links to a higher BMI. This is because staying on your phone limits how much activity you can be a part of. Physical activity is crucial to our lives as it helps us stay healthy and in shape, but too much screen time does the opposite. Physical activity can also lead to a sense of accomplishment which is good for your mental health. Social media is also shown to give you short bursts of dopamine, which only makes you feel good temporarily, unlike physical activity which has been proven to make people feel better.",

                         //info on depression
                         "😖;One of the main things that comes with technology is depression, and this is a major issue right now.;Depression seems to be directly linked to social media and technology. A study showed that people who spent more time on social had higher depression rates. There has also been a 33% increase in depression among 12th graders over the past few years, and an increase in suicide rates within females. Social media also leads to less face-to-face interactions, which has shown to decrease depression within people. Depression is also interconnected with many issues that come with technology such as, sleep deprivation, self-esteem, feelings of isolation, and anxiety. ",

                         //info on isolation
                         "🧍;Being on social media too much has been shown to lead to feelings of isolation.;Everyone wants to feel included, but social media does a terrific job of increasing people's fear of missing out. On social media we often see people going out for dinner, going to a party, or hanging out with friends. Seeing what other people are doing, and what you are not, can lead to feeling excluded. In adults aged 19-32, higher social media use resulted in being up to 3x more likely to feel isolated. Isolation also directly correlates with depression which is a whole problem in and of itself."};
    
    //randomizes the order of the array
      randomArr(probs, probs.length);
    //3 new string arrays to keep track of emojis, small info, and more info
      String [] emojis = new String[probs.length];
      String [] info = new String[probs.length];
      String [] moreInfo = new String[probs.length];
    //for loop to go through main problems array
      for (int i=0;i<probs.length;i++){
        //split array wherever there's a semi-colon and set each section to a new array
        String [] temp = probs[i].split(";");
        //set each section of the array to its appropriate array
        emojis[i]=temp[0];
        info[i]=temp[1];
        moreInfo[i]=temp[2];
      }
    //Two 2-d arrays that show how the user should input key values, and one clear board that can be used later
      String [][] DemoBoard = {{"[7]","[8]","[9]"}, 
                               {"[4]","[5]","[6]"},
                               {"[1]","[2]","[3]"}};
      String [][] eBoard = {{"[  ]","[  ]","[  ]"}, 
                            {"[  ]","[  ]","[  ]"},
                            {"[  ]","[  ]","[  ]"}};
    //Tells the user how to enter keys and prints appropriate array
      System.out.println("Enter answer in the following format:");
      pArray(DemoBoard);
    //while loop runs until user enters e
      while (true){
        //tels user to enter e to start game
      System.out.println("\nPress e to start");
      String cont = input.nextLine();
        //if user input = e break while loop
      if (cont.equalsIgnoreCase("e")) break;
      }
    //while loop runs while user has correct answer to memory game
      while (correct){
        //add 1 to level
        lvl++;
        x++;
        if (x==emojis.length){
          x=1;
        }
        //list to store random numbers
        List<Integer> ListRands = new ArrayList<Integer>();
        //for loop to run for the number of the level (eg. if lvl=1 for loop will run once)
        for (int i = 0;i<lvl;i++){
          String [][] board = {{"[  ]","[  ]","[  ]"}, 
                               {"[  ]","[  ]","[  ]"},
                               {"[  ]","[  ]","[  ]"}};
          //get random number between 0-8
          Random rn = new Random();
          int answer = rn.nextInt(9);
          //add one to answer to get number between 1-9 and then put it in the list
          ListRands.add(answer+1);
          //check the value of the number to put symbol in appropriate row and column
          //eg. if num is between 1 put symbol in first row 2nd column
          if (answer<=2){
            board[2][answer]="["+emojis[x-1]+"]";
          }
          else if (answer <=5){
            board[1][answer-3]="["+emojis[x-1]+"]";
          }
          else{
            board[0][answer-6]="["+emojis[x-1]+"]";
          }
          //clear screen
          cs();
          //print users level, and highscore
          System.out.println("LEVEL: "+lvl+"\tHighscore: LEVEL "+highscore);
          //prints board with symbol in place
          pArray(board);
          //waits one second then clears screen
          TimeUnit.SECONDS.sleep(1);
          cs();
          //prints level and highscore again, but with an empty board
          System.out.println("LEVEL: "+lvl+"\tHighscore: LEVEL "+highscore);
          pArray(eBoard);
          //after 100 milliseconds move on with program.
          TimeUnit.MILLISECONDS.sleep(100);
        }
        //clear screen
        cs();
        //for loop runs same number of times as the level
        for (int i=0;i<lvl;i++){
          //while loop to do error checking on user input
          while (true){
            //tells user to input position of the symbol
            System.out.println("Enter position of object in order, click e in between.");
            String iNum = input.nextLine();
            //makes input into an int
            uAns = numCheck(iNum);
            //if user input is not an int return an error message
            if (uAns==-1){
              cs();
              System.out.println("Invalid Input. Please Enter a Number.");
            }
              //else break while loop
            else{
              break;
            }
          }
          //checks if user input is equal to position of symbol
          if (uAns==ListRands.get(i)){
            //if it is continue
            continue;
          }
            //if users answer is wrong set correct to false and break while loop
          else{
            correct = false;
            break;
          }
        }
        //clear screen
        cs();
        //if user has lost give them a message
        if (!correct) System.out.println("Oops, you lost. Less time on your devices may help improve your memory.");
        //tells user some information on the symbol they saw
        System.out.println(emojis[x-1]+", "+info[x-1]);
        //asks user if they want to learn more or continue on
        System.out.println("Enter 'y' to learn more or 'e' to continue");
        //while loop for error checking
        while (true){
          //set get user input
          learn = input.nextLine();
          //if user input is e or y break the loop
          if (learn.trim().equalsIgnoreCase("e")||learn.trim().equalsIgnoreCase("y")) break;
          else System.out.println("Invalid input. Please enter 'y'or 'e'.");
        }
        //if user wants to learn more clear screen then tell them some more info
        if (learn.trim().equalsIgnoreCase("y")){
          cs();
          System.out.println(moreInfo[x-1]);
          cont();
        }
      }
    //return the users score
    return lvl;
  }
  //Global variables that represent text colours
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
  public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
  public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
  public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  
  /**
  This method prints out the login screen and gets user input
  @return This returns the option the user picked
  */
  public static int login(){
    //scanner for user input
    Scanner input = new Scanner (System.in);
    //array with all options with no change in colour
    String [][] login = {{"---------------\n   LOG IN   \n---------------"},
                         {"---------------\n   SIGN UP   \n---------------"},
                         {"---------------\n PLAY AS GUEST\n---------------"},
                        {"---------------\n    EXIT    \n---------------"}};
    //same array as above with first option in red
    String [][] loginC = {{ANSI_RED+"---------------\n   LOG IN   \n---------------"+ANSI_RESET},
                         {"---------------\n   SIGN UP   \n---------------"},
                         {"---------------\n PLAY AS GUEST\n---------------"},
                         {"---------------\n    EXIT    \n---------------"}};

    //tells user how to navigate the menu
    System.out.println("USE W & S TO MOVE\nCLICK ENTER BETWEEN EACH CLICK\nCLICK e TO SELECT OPTION");
    //print coloured array and set log variable to 0
    pArray(loginC);
    int log = 0;
    //while loop to check user input and move which option is coloured
    while (true){
      //makes currently coloured part no longer coloured
      loginC[log][0]=login[log][0];
      //get user input
      String i = input.nextLine();
      //e breaks loop
      if (i.equals("e")){
        break;
      }
      //w goes to above option
      else if (i.equalsIgnoreCase("w")){
        //only goes up if you are not already at the top
        if (log != 0){
          log -=1;
        }
      }
      //s goes to below option
      else if (i.equalsIgnoreCase("s")){
        //only moves down in you are not at the bottom
        if (log != 3){
          log+=1;
        }
      }
        //else print error message and restart loop
      else{
        System.out.println("INVALID INPUT: PLEASE USE W and S KEYS TO MOVE");
        continue;
      }
      //make selected option red then clear screen
      loginC[log][0] = (ANSI_RED+loginC[log][0]+ANSI_RESET);
      cs();
      //print updated array and tell user how to move again
      System.out.println("USE W & S TO MOVE\nCLICK ENTER BETWEEN EACH CLICK\nCLICK e TO SELECT OPTION");
      pArray(loginC);
    }
    //return option user picked
    return log;
  }

  /**
  This method can either print out a leaderboard sorted form highest score to lowest, or print all scores into the data file
  @param a This is the user score. If this is zero a leaderboard will be printed
  @param user This is the name of the user
  */
  public static void leaderboard(int a, String user){
    //decalare boolean and list
    boolean dupe = true;
    List<Integer> leader = new ArrayList<Integer>();
    //try for file IO
    try{
      //scanner to read through file
      Scanner fileInput = new Scanner (new File ("data.txt"));
      //while loop runs while there is another line in the file
      while(fileInput.hasNext()){
        //split the line and put values in another array
        String data = fileInput.nextLine();
        String [] info = data.split(",");
        //set user score to an int variable and add it to list
        int userH = Integer.parseInt(info[2]);
        leader.add(userH);
      }
      //sort list from highest to lowest score
      Collections.sort(leader, Collections.reverseOrder());
      //two new lists to store usernames and passwords
      List<String> users = new ArrayList<String>();
      List<String> passes = new ArrayList<String>();
      //while loop runs for the size of scores list
      for (int i=0; i<leader.size();i++){
        //scanner to read file
        Scanner second = new Scanner (new File("data.txt"));
        //while loop runs for number of lines
        while (second.hasNext()){
          //set dupe to true
          dupe = true;
          //split line and put sections into another array
          String data = second.nextLine();
          String [] info = data.split(",");
          //make user score an int
          int hCheck = Integer.parseInt(info[2]);
          //if we are on the same line as the given user, and their score is higher then 0 make the user score equal to their highscore
          if (info[0].equals(user)&&hCheck>=a){
            a=hCheck;
          }
          //check if user score is equals to value in scores list
          if (hCheck == leader.get(i)){
            //if it is run a for loop for the size of scores list
            for (int c=1;c<leader.size();c++){
              //this checks if the username is already in the users list. This stops the same name from appearing multiple times in the leaderboard
              if (i>=c){
                if (users.get(i-c).equals(info[0])){
                  dupe = false;
                }
              }
              //if there is no duplicate username break loop
              else break;
            }
            //if there is a duplicate continue loop
            if (!dupe)continue;
            //otherwise add the username and password into appropriate lists then break for loop
            users.add(info[0]);
            passes.add(info[1]);
            break;
          }
          
        }
      } 
      //if a is 0 print leaderboard
      if (a==0){
        System.out.println(ANSI_PURPLE+"# || USERNAME || SCORE"+ANSI_RESET);
        //for loop runs for size of scores loop
        for (int i = 1;i<=leader.size();i++){
          //print out each user, their rank, and their score
          //output has been formatted to show a certain number of characters
          System.out.printf("%-2d%s %-9.9s%s %-2d\n", i, ANSI_PURPLE+"||"+ANSI_RESET, users.get(i-1), ANSI_PURPLE+"||"+ANSI_RESET, leader.get(i-1));
        }
      }
        //if a is not 0 then rewrite data file with updated scores
      else {
        // filewriter and printwriter lets us print in text file
        FileWriter writer = new FileWriter ("data.txt");
        PrintWriter w = new PrintWriter(writer);
        //for loop runs for size of scores list
        for (int i=0;i<leader.size();i++){
          //if we are on last iteration of loop print normally instead of println
          if (i==(leader.size()-1)){
            //if we are on the part of the user list that is equal to the given user, print their given score into the file instead of the value we previously got.
            if (users.get(i).equals(user)){
              w.print(users.get(i) + "," + passes.get(i) + "," + a);   
            }
            //else just print username, password, and score into file
            else{
              w.print(users.get(i) + "," + passes.get(i) + "," + leader.get(i));
            }
          } 
          //SAME AS ABOVE EXCEPT USING PRINTLN BECAUSE IT IS NOT THE LAST LINE
          else{
            if (users.get(i).equals(user)){
              w.println(users.get(i) + "," + passes.get(i) + "," + a);   
            }
            else{
              w.println(users.get(i) + "," + passes.get(i) + "," + leader.get(i));
            }
          }
        }
        //close printwriter
        w.close();
        
      }
    }
      //catches file IO exception
    catch (IOException e){
      System.out.println("You're bad");
    }
  }

  /**
  This is the main method
  */
  public static void main(String[] args) throws InterruptedException{
    //start by clearing screen
    cs();
    //Declare most of the variables that will be used and open scanner for user input
    String password = "";
    Scanner input = new Scanner (System.in);
    boolean checkUser = true;
    boolean checkGuest = true;
    boolean keepPlay = true;
    int score = 0;
    int choice = 0;
    String user = "";
    //do while loop to make sure the program runs at least once
    do{
      // set booleans back to true
      checkGuest = true;
      checkUser = true;
      //run log in screen method
      int logV = login();
      //try catch for file IO errors
      try{
        //get highest score in text file
        int high = highscore();
        //This runs if user picked log in option
        if (logV==0){
          //first clear screen
          cs();
          //while loop runs until user enters a username in the database
          while (checkUser){
            //scanner to read text file
            Scanner fileInput = new Scanner (new File ("data.txt"));
            //tell user to enter username and get input
            System.out.println("Enter Username:");
            user = input.nextLine();
            //while loop runs for number of lines in file
            while(fileInput.hasNext()){
              //split data up into an array
              String data = fileInput.nextLine();
              String [] info = data.split(",");
              //checks if the username the user inputted is in the database
              if (info[0].equals(user)){
                System.out.println("TRUE");
                //if it is set password to the appropriate password
                password = info[1];
                //set variable to false and break while loop
                checkUser=false;
                break;
              }
            }
            //if user entered an invalid username tell them to try again
            if (checkUser == true) System.out.println("Username not in database. Please try again.");
          }
          //while loop runs until user enters correct password
          while (true){
            //tell user to input password and get their input
            System.out.println("Enter Password: ");
            String uPass = input.nextLine();
            //if user input is equal to the correct password break while loop
            if (uPass.equals(password)){
              System.out.println("Correct!");
              break;
            }
              //else print error message and continue looping
            else{
              cs();
              System.out.println("Incorrect Password, try again.");
            }        
          }
        }

        //This runs if user entered sign up option
        else if(logV==1){
          //first clear screen
          cs();
          //Tells the user they are creating a user then asks them to click e to continue
          System.out.println("You are now creating a new user");
          cont();
          //set user to an empty string and create a new variable for user password
          user = "";
          String uPass = "";
          //while loop runs until user enters a username not in the database
          while (true){
            //set boolean to true
            checkUser = true;
            //tell user to enter their username
            System.out.println("Enter desired user name: ");
            user = input.nextLine();
            //use while loop similar to previous section to check if username is in the text file
            Scanner fileInput = new Scanner (new File ("data.txt"));
            while(fileInput.hasNext()){
              String data = fileInput.nextLine();
              String [] info = data.split(",");
              //if it is tells user the username is take and sets boolean to false and breaks loop
              if (info[0].equals(user)){
                System.out.println("Username already taken. Please try again.");
                checkUser = false;
                break;
              }
              //if check user is true break while loop
            }
            if (checkUser) break;
          }
          //while loop runs until user enters a valid password
          while (true){
            //tells user password requirements then gets their password
            System.out.println("Enter Password (Must include at least 1 letter then 1 number): ");
            uPass = input.nextLine();
            //use regular expression to make sure user input has at least one letter followed by at least one number
            Pattern pattern = Pattern.compile("([a-z]+)([0-9]+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(uPass);
            boolean matchFound = matcher.find();
            //if user entered a valid password break loop
            if (matchFound) break;
              //else give user error message
            else{ 
              cs();
              System.out.println("Password does not meet requirements.");
            }
          }
          //filewriter to writer into text file
          FileWriter writer = new FileWriter("data.txt",true);
          //enter new users username and password into text file then close filewriter
          writer.write("\n"+user+","+uPass+",0");
          writer.close();
        }

        //This runs if user chooses to play as a guest
        else if(logV == 2){
          //clear screen
          cs();
          //while loop to error check
          while (true){
            //warns user their highscore will not be saved in guest mode and asks if they want to continue and gets user input
            System.out.println("Playing as a guest means your highscore will not save, and will not appear on the leaderboard.\nDo you want to continue?(y/n)");
            String cont = input.nextLine();
            //if user wants to continue break loop
            if (cont.equalsIgnoreCase("y")) break;
            //if not set checkGuest to false and break while loop, this will take user back to the main menu
            else if (cont.equalsIgnoreCase("n")){
              checkGuest = false;
              break;
            }
            //if user inputted invalid input, tell them then get input again.
            else {
              cs();
              System.out.println("Invalid Input. Please enter y or n");
            }
          }
          //if check guest is false start loop from the beginning
          if (checkGuest == false){
            cs();
            continue;
          }
        }
        //if user picked exit option end the program and print an exit message
        else if(logV==3){
          cs();
          System.out.println("Goodbye!");
          break; 
        }
        cs();

      //while loop runs while user wants to keep playing the game
        while (keepPlay){
          //run memory game method and set user score to a new variable
          int userScore = memory(high);
          //clear screen
          cs();
          //checks if users score in the memory game is higher than the highscore
          if (userScore>high){
            //if it is set the new highscore to the userscore
            score = userScore;
            //tell the user they beat the highscore
            System.out.println("New Highscore!");
            //if the user was playing as a guest tell them there high score won't save
            if (logV==2) 
              System.out.println("Unforunately as you're not logged in, your highscore will not save.");
            //print user score and highscore
            System.out.println("Score: "+userScore+"\nHigh Score: "+score);
          }
            //if user did not beat high score make score equal to highscore and print user score and high score
          else{
            score = high;
            System.out.println("Score: "+userScore+"\nHigh Score: "+score);
          }
          //checks if user has made an account 
          if (logV==0||logV==1){
            //uses leaderboard method to update text file with new user score
            leaderboard(userScore, user);
          }
          //makes user press e to continue
          cont();
          //while loop runs until user picks an option in the following lines of code
          while (true){
            /**
            ---------------
            THIS SECTION RUNS EXACTLY THE SAME AS LOG IN METHOD BUT WITH DIFFERENT OPTIONS
            BECAUSE OF THIS, I HAVE NOT COMMENTED LINES 701-736
            ---------------
            */
            String [][] opt = {
                         {"---------------\n  PLAY AGAIN  \n---------------"},
                         {"---------------\n LEADERBOARD \n---------------"},
                        {"---------------\n  MAIN MENU  \n---------------"}};
            String [][] optC = {{ANSI_RED+"---------------\n  PLAY AGAIN  \n---------------"+ANSI_RESET},
                                 {"---------------\n LEADERBOARD \n---------------"},
                                 {"---------------\n  MAIN MENU  \n---------------"}};
            
            System.out.println("USE W & S TO MOVE\nCLICK ENTER BETWEEN EACH CLICK\nCLICK e TO SELECT OPTION");
            pArray(optC);
            choice = 0;
            while (true){
              optC[choice][0]=opt[choice][0];
              String i = input.nextLine();
              if (i.equals("e")){
                break;
              }
              else if (i.equalsIgnoreCase("w")){
                if (choice != 0){
                  choice -=1;
                }
              }
              else if (i.equalsIgnoreCase("s")){
                if (choice != 2){
                  choice+=1;
                }
              }
              else{
                System.out.println("INVALID INPUT: PLEASE USE W and S KEYS TO MOVE");
                continue;
              }
              optC[choice][0] = (ANSI_RED+optC[choice][0]+ANSI_RESET);
              cs();
              System.out.println("USE W & S TO MOVE\nCLICK ENTER BETWEEN EACH CLICK\nCLICK e TO SELECT OPTION");
              pArray(optC);
            }
            //if user picked play again, break while loop and clear screen
            //this will restart the game
            if (choice==0){
              cs();
              break;
            }
              //checks if user picked learboard
            else if (choice==1){
              cs();
              //prints out the leaderboard using the method
              leaderboard(0, "");
              cont();
              
            }
            //checks if user selected main menu
            else if (choice==2) {
              cs();
              //makes check guest false and breaks the while loop
              checkGuest = false;
              break;
            }
          }
          //if user clicked main menu break this while loop as well
          if (choice==2) break;
        }
        //catch for IO errors
      } catch(IOException e){
        System.out.println("you're bad.");
      }
      //This is a do while so while checkGuest is false, keep running the game
    }while (!checkGuest);
  }
}