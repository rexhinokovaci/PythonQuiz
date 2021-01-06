package com.modex.pythonquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.modex.pythonquiz.QuizContractClass.*;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PythonQuiz.db";
    public static final int DATABASE_VERSION = 1;


    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;


        final String SQL_CREATE_QUESTIONS_TABLE = " CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                QuestionsTable.COLUMN_QUESTIONS + " TEXT , " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT , " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT , " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT , " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT , " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER ," +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT " +
                " ) ";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);

    }

    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // make it look simple and not complicated

//    Here WE can add questions !!!



    private void fillQuestionsTable () {

        QuestionsClass q1 = new QuestionsClass("What will the following program print out: \n >>> x = 15\n" +
                ">>> x = x + 5\n" +
                ">>> print x","10","20","30","40",2,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q1);
        QuestionsClass q2 = new QuestionsClass("Python scripts (files) have names that end with:","pt","po","py","ph",3,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q2);
        QuestionsClass q3 = new QuestionsClass("Which of these words is a reserved word in Python","for","name","reserved","point",1,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q3);
        QuestionsClass q4 = new QuestionsClass("What is the proper way to say \"good-bye\" to Python?","end()","bye()","done()","quit()",4,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q4);
        QuestionsClass q5 = new QuestionsClass("Which of the parts of a computer actually execute the program instructions?","SSD","CPU","RAM","ROM",2,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q5);
        QuestionsClass q6 = new QuestionsClass("What is the difference between a list and a tuple?","a tuple is immutable and a list is not","a list is immutable and a tuple is not","both of them are immutable","a list is not immutable and a tuple is",1,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q6);
        QuestionsClass q7 = new QuestionsClass("What will the following program print out: print ('5' * 3)","15","555","125","'5'*3",2,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q7);
        QuestionsClass q8 = new QuestionsClass("What will the following program print out: print (5 * 15)","75","5","15","5 * 15",1,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q8);
        QuestionsClass q9 = new QuestionsClass("What is output of: \n" +
                "33 == 33.0","True","False","Error","Null",1,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q9);
        QuestionsClass q10 = new QuestionsClass(" What is output for following code:\n" +
                "y = [4, 5,1j]\n" +
                "y.sort()","[1j,4,5]","[4,5,1j]","[5,4,1j]","Error",4,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q10);
        QuestionsClass q11 = new QuestionsClass("How can we check whether the object is instance of class or not. Let us consider an object O which is instance of class B.","B.isinstance(O)","isinstance(O,B)","O.isinstance(B)","isinstance(B,O)",2,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q11);
        QuestionsClass q12 = new QuestionsClass("What is output of following −\n" +
                "print('any'.encode())","‘any’","x’any’","b’any’","‘yan’",3,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q12);
        QuestionsClass q13 = new QuestionsClass("Suppose we are given with two sets(s1&s2) then what is the output of the code: S1 + S2 ","It is an illegal command.","Adds the elements of the both the sets.","Removes the repeating elements and adds both the sets","Output will be stored in S1.",1,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q13);
        QuestionsClass q14 = new QuestionsClass("Is python a low level programming language?'","Yes","No","Middle Ground","None",2,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q14);
        QuestionsClass q15 = new QuestionsClass("What is linear search?","Steps to determine an object in a list","Algorithm to check sort()","Algorithm to find the least value","Algorithm to find a name appear on a list",4,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q15);
        QuestionsClass q16 = new QuestionsClass("You ________ a function to execute it","call","delete","import","create",1,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q16);
        QuestionsClass q17 = new QuestionsClass("A ____ is a variable that is created inside a function.","Global variable","Local variable","class","package",2,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q17);
        QuestionsClass q18 = new QuestionsClass("A(n) _______ is a piece of data that is sent into a function.","tuple","parameter","variable","argument",4,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q18);
        QuestionsClass q19 = new QuestionsClass("A variable that is visible to every function in a program file is a","Global variable","Local variable","Object","Function",1,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q19);
        QuestionsClass q20 = new QuestionsClass("When possible, you should avoid using _______ variables","Local","Global","Private","Static",2,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q20);
        // SUNDAY
        QuestionsClass q21 = new QuestionsClass("What is the difference between a list and a dictionary?","list are immutable, dictionaries are mutable","dictionaries may have non-integer keys","lists have integer keys","dictionaries don't have integer keys",2,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q21);
        QuestionsClass q22 = new QuestionsClass("Dictionaries can store complex types, but every item must be of the same type.","True","False","True but only with Integers","False but only with Strings",2,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q22);
        QuestionsClass q23 = new QuestionsClass("What Does this Code Produce: "+
                "a = { }\n" +
                "then\n" +
                "a['First'] = 'Bob'\n" +
                "a['Last'] = 'Smith'\n" +
                "print(a)\n" +
                "\n" ,"{'Last': 'Bob', 'First': 'Smith'}","{'Last': 'Smith', 'First': 'Smith'}","{'Last': 'Smith', 'First': 'Bob'}","{'Last': 'Bob', 'First': 'Smith'}",3,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q23);
        QuestionsClass q24 = new QuestionsClass("What does this code produce: " +
                "If\n" +
                "a = { 'First' : 'Bob', 'Last' : 'Smith' }\n" +
                "then\n" +
                "print(a['Middle'])\n" +
                "\n",
                "\"{'First': 'Bob', 'Last': 'Smith'}","{'Last': 'Smith', 'First': 'Bob'}","'{First': 'Smith', 'First': 'Smith'}","an error",4,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q24);
        QuestionsClass q25 = new QuestionsClass("print( \"2\" * 3)\n" +
                "\n" +
                "produces: ","6","8","333","222",4,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q25);

        // Choose All "Easy" Categories
        QuestionsClass q26 = new QuestionsClass("What is int?","Integer","String","Boolean","Float",1,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q26);
        QuestionsClass q27 = new QuestionsClass("what is float used for?","Decimal Number","Floating Numbers","Large Numbers","Integer Numbers",2,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q27);
        QuestionsClass q28 = new QuestionsClass("What is Boolean","Find Characters","Determine the values","Find true and false values","Find the attributes of a value",3,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q28);
        QuestionsClass q29 = new QuestionsClass("What is String","Numbers","Boolean","Floating Numbers","Characters",4,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q29);
        // Sunday Easy 1
        QuestionsClass q30 = new QuestionsClass("What specifies ow the symbols of the language are allowed to be put together","Syntax","Intend Space","Compiler","IDE",1,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q30);
        QuestionsClass q31 = new QuestionsClass("what is a group of lines (like \"chunks\" of code in Scratch)","Syntax","Blocks","Trash","Rubbish",2,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q31);
        QuestionsClass q32 = new QuestionsClass("What is it called when a comment is used at the beginning of a function definition","XML-UTF","Main Method","Docstring","Class",3,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q32);
        QuestionsClass q33 = new QuestionsClass("What is a program that does the testing for you","Espresso","Robo Test","Devices","Test Suite",4,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q33);
        //Sunday Easy 2
        QuestionsClass q34 = new QuestionsClass("What we use for quotation marks (' or \")","String","Tuple","Int","Boolean",1,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q34);
        QuestionsClass q35 = new QuestionsClass("what we use for parentheses ( )","String","Tuple","Dictionaries","Frozen Sets",2,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q35);
        QuestionsClass q36 = new QuestionsClass("What we use for square brackets [ ]","String","Tuple","Lists","Sets",3,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q36);
        QuestionsClass q37 = new QuestionsClass("What we use for curly braces { }","Tuple","String","Lists","Dictionaries",4,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q37);
        //Sunday Easy 3
        QuestionsClass q38 = new QuestionsClass("def <function name>:","Indicates the beginning of a function definition","Indicates the beginning of a class","Indicates the end of a function","Indicates the end of a class",1,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q38);
        QuestionsClass q39 = new QuestionsClass("The Boolean operator that only returns true if BOTH of its operands are true.","is","and","greater","less",2,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q39);
        QuestionsClass q40 = new QuestionsClass("The Boolean operator that returns true if EITHER of its operands are true","and","is","or","equals",3,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q40);
        QuestionsClass q41 = new QuestionsClass("The Boolean operator that returns true if its single operand is false","or","and","is","not",4,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q41);
        //Sunday Easy 4
        QuestionsClass q42 = new QuestionsClass("What type of value is enclosed in quotation marks?","strings","integers","boolean","floats",1,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q42);
        QuestionsClass q43 = new QuestionsClass("Can you can a Python keyword as the name for a variable","Yes","No","Private Classes","Public Classes",2,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q43);
        QuestionsClass q44 = new QuestionsClass("The process of formulating a problem, finding a solution, and expressing the solution","problem finder","architecture problem","problem solving","flow-chart",3,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q44);
        QuestionsClass q45 = new QuestionsClass("A programming language like Python that is designed to be easy for humans to read and write","Low-level","Stationary","High-Stationary","High Level",4,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q45);
        //Sunday Medium 0
        QuestionsClass q46 = new QuestionsClass("print('Bob' 'Smith')\n" +
                "\n" +
                "produces the same output as","print('Bob'+'Smith')","print('Bob','Smith')","print('Smith'+'Bob')","print('Smith','Bob')",1,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q46);
        QuestionsClass q47 = new QuestionsClass("What does\n" +
                "\n" +
                "print(\"%.2f\" % 10.50)\n" +
                "\n" +
                "produce?","10.0","10.50","11.0","11.50",2,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q47);
        QuestionsClass q48 = new QuestionsClass("What does\n" +
                "\n" +
                "print(\"Hello %s, I see you are %s years old\" % ('Bob', 9))\n" +
                "\n" +
                "produce?","Hello 9, I see you are Bob years old","Hello old, I see you are Bob years 9","Hello Bob, I see you are 9 years old","Hello Bob, I see you are Bob years old",3,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q48);
        QuestionsClass q49 = new QuestionsClass("What does\n" +
                "\n" +
                "print(\"%08.2f\" % 10.50)\n" +
                "\n" +
                "produce?","00.01","00000.10","00000.1050","00010.50",4,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q49);
        //Sunday Medium 1
        QuestionsClass q50 = new QuestionsClass("What does\n" +
                "\n" +
                "print(\"%5s\" % 'Smith and Wesson')\n" +
                "\n" +
                "produce?","Smith and Wesson","Wesson and Smith","boolean","floats",1,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q50);
        QuestionsClass q51 = new QuestionsClass("What does\n" +
                "\n" +
                "print('%x' % 255)\n" +
                "\n" +
                "produce?","xx","ff","null","error",2,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q51);
        QuestionsClass q52  = new QuestionsClass("If\n" +
                "\n" +
                "x = 2\n" +
                "\n" +
                "then what does\n" +
                "\n" +
                "print(x >3)\n" +
                "\n" +
                "produce","True","Null","False","Error",3,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q52);
        QuestionsClass q53 = new QuestionsClass("If\n" +
                "\n" +
                "x = 25\n" +
                "\n" +
                "then what does\n" +
                "\n" +
                "if x>=21:\n" +
                "\n" +
                "print(\"yes, you may have that beer\")\n" +
                "\n" +
                "else:\n" +
                "\n" +
                "print(\"sorry buddy, you are not old enough\")\n" +
                "\n" +
                "\n" +
                "produce?","sorry buddy, you are not old enough","yes, buddy you are not old enough","no, you may have that beer","yes, you may have that beer",4,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q53);
        //Sunday Medium 2
        QuestionsClass q54 = new QuestionsClass("The code:\n" +
                "\n" +
                "for i in range(0, 4):\n" +
                "\n" +
                "print(i)\n" +
                "\n" +
                "\n" +
                "produces?","0 1 2 3","0 1 2 3 4","1 2 3 4","0 4",1,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q54);
        QuestionsClass q55 = new QuestionsClass("The code:\n" +
                "\n" +
                "x = ['Bob', 'Doug']\n" +
                "\n" +
                "print(\"The Mackenzie brothers are: \")\n" +
                "\n" +
                "for i in x:\n" +
                "\n" +
                "print(i)\n" +
                "\n" +
                "produces","Bob, Doug are The Mackenzie Brothers","The Mackenzie brothers are: Bob, Doug","Bob, Doug are Brothers","The Mackenzie brothers are: Doug, Bob",2,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q55);
        QuestionsClass q56 = new QuestionsClass("The code:\n" +
                "\n" +
                "count = 0\n" +
                "\n" +
                "while count < 5:\n" +
                "\n" +
                "print(count)\n" +
                "\n" +
                "count += 1\n" +
                "produces","0 1 2 3 4 5","1 2 3 4 5","0 1 2 3 4","0 2 4 8 16",3,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q56);
        QuestionsClass q57 = new QuestionsClass("The code:\n" +
                "\n" +
                "for x in range(0, 10):\n" +
                "\n" +
                "if x % 2 == 0:\n" +
                "\n" +
                "continue\n" +
                "\n" +
                "print(x)\n" +
                "produces","1 3 5 9 15","1 3 5 9 12","1 3 5 9 14","1 3 5 7 9",4,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q57);
        //Sunday Medium 3
        QuestionsClass q58 = new QuestionsClass("What does the following code produce?\n" +
                "\n" +
                "def myfunc(a, b, c='c'):\n" +
                "\n" +
                "print(a, b, c)\n" +
                "\n" +
                "myfunc(1, 'of')","1 of c","1 of a","error","1 of b",1,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q58);
        QuestionsClass q59 = new QuestionsClass("What does the following code produce?\n" +
                "\n" +
                "\n" +
                "def myfunc(a='a', b, c=4):\n" +
                "\n" +
                "print(a, b, c)\n" +
                "\n" +
                "myfunc('yes', 'you')","yes, a","an error","yes, c","yes, b",2,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q59);
        QuestionsClass q60 = new QuestionsClass("What does the following code do?\n" +
                "\n" +
                "f = open('myfile.txt')","opens a file for writing only","opens a file for read and write","opens a file for reading only","opens a file for writing only",3,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q60);
        QuestionsClass q61 = new QuestionsClass("The following code\n" +
                "\n" +
                "f = open('myfile.txt', 'w')\n" +
                "f.write('foo')\n" +
                "f.write('bar')\n" +
                "print(f.read())\n" +
                "produces","foo","bar","foobar","an error",4,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q61);
        //Sunday Medium 4
        QuestionsClass q62 = new QuestionsClass("The file seek function is used to","go to another position in a file","to preview the file","to copy the file","to delete the file",1,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q62);
        QuestionsClass q63 = new QuestionsClass("What does the file tell function do?","doesn't report the position in a file where next file operation will occur","reports the position in a file where next file operation will occur","will copy the file attributes","will delete the file attributes",2,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q63);
        QuestionsClass q64 = new QuestionsClass("Which of the following is a way to tell if a file is open?","collect info from check()","not allowed","check its opened attribute","check its public files",3,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q64);
        QuestionsClass q65 = new QuestionsClass("Files opened for writing are automatically truncated if they exist.","True","False","Only on some methods","Private on some degrees",1,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q65);
        //Sunday Hard 0
        QuestionsClass q66 = new QuestionsClass("Given the following code, how would you create a new dog object?\n" +
                "class Dog:\n" +
                "def __init__(self, name='Fido'):\n" +
                "\n" +
                "self.name=name\n" +
                "def bark():\n" +
                "print('Woof')","myDog = Dog('Betsy')","myDog = bark('Betsy')","myDog = Dog.bark('Betsy')","myDog = bark.Dog('Betsy')",1,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q66);
        QuestionsClass q67 = new QuestionsClass("Given the following code appears in my script\n" +
                "import foo.bar\n" +
                "how would the gamma function in the bar module be called in my script?","bar.foo.gamma","foo.bar.gamma","gamma.bar.foo","gamma.bar.foo",2,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q67);
        QuestionsClass q68 = new QuestionsClass("Given the following code in my script\n" +
                "from foo import bar\n" +
                "how would you call the gamma function in the bar module?","foo.gama","gamma.foo","bar.gamma","bar.foo",3,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q68);
        QuestionsClass q69 = new QuestionsClass("What is output for\n" +
                "\n" +
                "'raining'. find('z') ?","2","1","0","-1",4,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q69);
        //Sunday Hard 1
        QuestionsClass q70 = new QuestionsClass("What will be the output?\n" +
                "def func(x, ans):\n" +
                "> if(x==0):\n" +
                "> return 0\n" +
                "> else:\n" +
                "> return func(x-1, x+ans)\n" +
                "print(func(2,0))","0","error","1","01",1,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q70);
        QuestionsClass q71 = new QuestionsClass("What will be printed out from the code?\n" +
                "x = 0\n" +
                "x = 'S'\n" +
                "print(x)","0","S","0,S","S,0",2,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q71);
        QuestionsClass q72 = new QuestionsClass("hat will be printed out from the code?\n" +
                "fruits = [\"apple\", \"banana\", \"cherry\"]\n" +
                "for x in fruits:\n" +
                "print(x)","apple, cherry, banana","cherry, apple, banana","apple, banana, cherry","apple, cherry, banana",3,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q72);
        QuestionsClass q73 = new QuestionsClass("What will be printed out from the code?\n" +
                "fruits = [\"apple\", \"banana\", \"cherry\"]\n" +
                "for x in fruits:\n" +
                "> print(x)\n" +
                "> if x == \"banana\":\n" +
                "> break","apple, apple","banana, apple","banana, banana","apple, banana",4,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q73);
        // Sunday Hard 2
        QuestionsClass q74 = new QuestionsClass("What is the output?\n" +
                "a = 33.0\n" +
                "b = 33\n" +
                "if b > a:\n" +
                "> print(\"b is greater than a\")\n" +
                "elif a == b:\n" +
                "> print(\"a and b are equal\"","a and b are equal","b is greater than a","a is greater than b","error",1,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q74);
        QuestionsClass q75 = new QuestionsClass("What will be the output of the following Python code? " +
                "print('Hello!2@#World'.istitle())","True","False","None","error",1,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q75);
        QuestionsClass q76 = new QuestionsClass("What will be the output of the following Python code? \n print('1Rn@'.lower())","n","r","1rn@","rn",3,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q76);
        QuestionsClass q77 = new QuestionsClass("What will be the output of the following Python code? \n print('''\n" +
                " \\tfoo'''.lstrip()) ","\\tfoo","ooft","none","foo",4,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q77);
        // Sunday Hard 3
        QuestionsClass q78 = new QuestionsClass("What will be the output of the following Python code? \n print('xyyzxxyxyy'.lstrip('xyy')) ","zxxyxyy","z","zxxy","error",1,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q78);
        QuestionsClass q79 = new QuestionsClass("What will be the output of the following Python code? \n print('cba'.maketrans('abc', '123')) ","{65: 49, 66: 50, 67: 51}","{97: 49, 98: 50, 99: 51}","123","321",2,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q79);
        QuestionsClass q80 = new QuestionsClass("What will be the output of the following Python code? \n print('abcdef'.partition('cd'))","(‘ab’, ‘ef’)","(‘abef’)","(‘ab’, ‘cd’, ‘ef’)","2",3,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q80);
        QuestionsClass q81 = new QuestionsClass("What will be the output of the following Python code? \n print('abcdefcdgh'.partition('cd'))","(‘ab’, ‘cd’, ‘ef’, ‘cd’, ‘gh)","(‘abcdef’, ‘cd’, ‘gh’)","(‘ac’, ‘bd’, ‘efcdgh’)","(‘ab’, ‘cd’, ‘efcdgh’)",4,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q81);
        // Sunday Hard 4
        QuestionsClass q82 = new QuestionsClass("What will be the output of the following Python code? \n x = ['ab', 'cd']\n" +
                "print(len(list(map(list, x)))))) "," 2","4","Error","None",3,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q82);
        QuestionsClass q83 = new QuestionsClass("What will be the output of the following Python code? \n x = ['ab', 'cd']\n" +
                "print(list(map(list, x))) ","[‘a’, ‘b’, ‘c’, ‘d’]","[[‘a’, ‘b’], [‘c’, ‘d’]]","[[‘ab’], [‘cd’]]","none of the mentioned",2,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q83);
        QuestionsClass q84 = new QuestionsClass("What will be the output of the following Python code? \n x = [12, 34]\n" +
                "print(len(list(map(len, x))))","2","1","error","None",3,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q84);
        QuestionsClass q85 = new QuestionsClass("What will be the output of the following Python code? \n x = [12, 34]\n" +
                "print(len(list(map(int, x))))","0","1","-1","2",4,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q85);

        // Sunday 1 EASY + 1 MEDIUM
        QuestionsClass q86 = new QuestionsClass("What is a regular expression?","syntax to define the code","chunks of code in a function","The ability to take several forms","a way of specifying matching patterns",4,QuestionsClass.DIFFICULTY_EASY);
        AddQuestion(q86);
        QuestionsClass q87 = new QuestionsClass("In a regular expression the asterisks (*) specifies","Repeated Values","Multiple Expressions","zero or more repetitions of the preceding reentrancy","0",3,QuestionsClass.DIFFICULTY_MEDIUM);
        AddQuestion(q87);

        //Sunday Hard Extra 5
        QuestionsClass q88 = new QuestionsClass("What will be the output of the following Python code? \n x = [12, 34]\n" +
                "print(len(''.join(list(map(str, x))))) ","4","2","1","0",1,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q88);
        QuestionsClass q89 = new QuestionsClass("What will be the output of the following Python code? \n x = [12, 34]\n" +
                "print(len(' '.join(list(map(int, x)))))","0","2","6","error",4,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q89);
        QuestionsClass q90 = new QuestionsClass("What will be the output of the following Python code? \n  [12.1, 34.0]\n" +
                "print(len(' '.join(list(map(str, x)))))","0","3","9","21",3,QuestionsClass.DIFFICULTY_HARD);
        AddQuestion(q90);



    }

    private void AddQuestion(QuestionsClass questionsClass) {

        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTIONS, questionsClass.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, questionsClass.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, questionsClass.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, questionsClass.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, questionsClass.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, questionsClass.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, questionsClass.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);

    }




    public ArrayList<QuestionsClass>getQuestions(String difficulty) {
        ArrayList<QuestionsClass> questionsClassesList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};


        Cursor cursor = db.rawQuery(" SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " +
                QuestionsTable.COLUMN_DIFFICULTY + " = ? ",selectionArgs );


        if (cursor.moveToFirst()) {
            do {

                QuestionsClass questionsClass = new QuestionsClass();
                questionsClass.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTIONS)));
                questionsClass.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                questionsClass.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                questionsClass.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                questionsClass.setOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                questionsClass.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionsClass.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionsClassesList.add(questionsClass);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionsClassesList;
    }

}


