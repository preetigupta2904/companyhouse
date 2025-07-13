# companyhouse

Setup Instructions

Step 1: Install Java (if not already)

Check if Java is installed:
java -version

if not installed, install the latest version:
brew install openjdk

Then add it to your shell config (e.g., .zshrc or .bash_profile):
export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"

Step 2:Install Chrome and ChromeDriver

Check your Chrome Version:
Open chrome://version/ in Chrome.

Install matching ChromeDriver:
brew install --cask chromedriver

Then add to path:
chmod +x /path/to/chromedriver
sudo mv /path/to/chromedriver /usr/local/bin/

Check version:
chromedriver --version

Step 3: Install IntelliJ IDEA
Download and install from: 
https://www.jetbrains.com/idea/download

Step 4: Clone the Repository:

Open Terminal and run:

git clone https://github.com/preetigupta2904/companyhouse.git

Step4: Setup Project in InteliJ

Open IntelliJ IDEA


Click "Open"


Navigate to the folder just cloned


Select it → Click "Open"

Once project is open it starts building dependencies
 







