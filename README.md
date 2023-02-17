# RPG-Game

### ➢ Game
In the "run" method, I create a list of accounts, a dictionary, and a list of image names used for the graphical interface using the "JsonInput" and "JsonInputPicture" classes, in which I have read the data from a .json file. Depending on the user's choices, I call either the "options" method or the "optionsGraphic" method.

The "options" method represents the menu with the moves and choices that the player can make and executes the indicated commands. The "options" method models the user's instructions using the graphical interface.

In addition to the "stories" method that displays a story based on the cell, I have also added the "startRules" method that displays the rules and instructions of the game for the console mode. By importing "java.util.concurrent.TimeUnit," I displayed the rules with a small delay to give the player the opportunity to read the instructions.

### ➢ Grid
In the map generation method, the number of enemies and shops was randomly generated so that I have at least 2 shops and 4 enemies, but they fit within the space provided by the game board. In the methods for traversing the squares, I added money to the player's backpack if the new cell was empty and had not been visited before.

I added a function to display the game board using the following symbols: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ ◉ - player <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ ? - unvisited cell <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ _ - visited cell or empty cell <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ F - Finish <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ E - Enemy <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ S - Shop <br>

### ➢ Entity
I have a String variable that stores the name of the image used in the graphical interface for the "Entity" object. In the method for using the ability, I check if the enemy (against whom the ability is used) is immune to that ability (in this case, the enemy does not receive damage) and I call the "receiveDamage" method, taking into account whether it is a normal attack or an ability.

### ➢ Character
I also added an Int variable to keep track of the number of defeated enemies, a variable used in displaying details about the character in the graphical interface.

Besides, I added two methods: "victoryGold" adds money to the inventory if the player wins a fight (with an 80% chance), and "upgradeLevel" increases the level (and consequently the power, charisma, and dexterity) based on the player's experience (experience increases after each victory and if the player finds the "Finish" cell).

### ➢ RandomInteger
This class contains a method that generates a random integer between two specified values. The class was used to generate all variables with a random value specified in the requirements.

### ➢ Factory
This is the class used to create the Factory design pattern, and with its help, I create characters.

### ➢ JsonInput, JsonInputPictures, JsonOutput
These are the classes where I read the list of accounts and list of image names from JSON files and also wrote the new changes made to the accounts and the list of accounts in the "accounts.json" file. To do this, I implemented the library that can be found in the source file, along with the .json files containing the data about accounts and used images.

### ➢ Main
This is a class that introduces the graphical interface. The window contains the game image and a button that sends the user to the account selection. The class was created for the appearance of the game.

### ➢ SelectAccount
The window contains a TextField where the selected account name will appear from a JScrollPane. In addition to the TextField and the account drop-down list, there are two buttons - one to go to the login page if an account is selected from the list, and one to go to the page for creating a new account. This class is also used to select a character from the character list of the selected account. The button for adding a new character is also present here.

### ➢ NewGraphicAccount
This is the window that allows the user to create a new account. Creation is only allowed if the required elements (name, password, and email address) have been entered. After successfully creating the account, the user is returned to the account selection page.

### ➢ NewGraphicCharacter
This is the window that allows the user to create a new character for the selected account. Creation is only allowed if the character name has been entered and the character type - Warrior, Rogue, or Mage - has been selected.

### ➢ Login
Here, the user enters the email and password associated with the selected account. If the credentials are confirmed to be correct, the user proceeds to the page for selecting a character for the game from the character list of the selected account. If the entered data is incorrect, an appropriate message is displayed.

### ➢ Table
The game board is represented by using multiple buttons. Each cell in the Grid map was a button. The button where the player is located has an emoji ninja icon, and the background color is turquoise. If there is also an enemy/store in that cell, when the mouse passes over that button, the icon will change to a suggestive image (button.setRolloverIcon). Next to the table are four other buttons representing the directions of movement, as well as JTextFields with details about the character.

### ➢ ShopGraphic
This is a window that contains details about the player's inventory and the potions for sale in the shop. Each potion corresponds to a button that allows the player to buy it. There is also an exit button if the player does not want to purchase a potion.

### ➢ BattleGraphic
This is the window where the battle between the player and the enemy is simulated. The details of both (current health and current mana) are displayed, as well as 3 buttons representing the player's possibilities (attack, use ability, use potion).

### ➢ AbilityGraphic
Displays details about the player's 3 abilities (mana cost and damage offered to the enemy). For each ability, there is a selection button that applies the effect of that ability.

### ➢ PotionGraphic
This is similar to the ShopGraphic. The potions from the player's inventory and details about them are displayed. Similarly, each potion has a button that when pressed, either regenerates health or mana, depending on the type of potion it represents and its details (regeneration value).

### ➢ ShowCharacter
This is the final page of the interface. It provides details about the player at the end of the game (accumulated experience, level reached, number of enemies defeated, number of coins earned). By pressing the "EXIT" button, the accounts with the new modifications made to the selected account will be written to the accounts.json file.

### ➢ Graphical Interface
The graphical interface was primarily created using the Swing component. The most commonly used components were JButton, JScrollPane, JTextField, JLabel, JPanel, and JSplitPane. This is accompanied by messages, instructions, and stories in the terminal.

### ➢ Tests
Each test has a specific name for the classes they test. "TestGame" is the class that starts the final game, without the hard-coded part, and simulates the game.
