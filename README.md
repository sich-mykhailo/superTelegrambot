<h1>Description</h1>
"Super Telegram Bot" is a Telegram bot that communicates with users and can provide results in the form of an Excel spreadsheet on Google Drive. This program interacts with other instances parsers that process the requests.
<br>
<h2></h2>
To get started, please access the Telegram bot by following this link : https://t.me/turbo_parser_bot
<h2></h2>
The description for this diagram can be found here: https://github.com/sich-mykhailo/parser in README file
<img width="1173" alt="Screenshot 2023-01-07 at 17 49 24" src="https://user-images.githubusercontent.com/11314278/211543063-7e26ca6e-a803-469d-8b68-f929100c690a.png">
<h2>Run applicatiion</h2>
1. Install Docker on your computer. <br>
2. Create an image and name it 'super-telegram-bot:latest' (use the tag specified in the Docker Compose file).<br>
3. Create a .env file and insert the following environment variables:<br>


a) You have to adjust [account in AWS](https://aws.amazon.com/console/) and configure SQS<br>
 - AWS_ACCESS_KEY<br>
 - AWS_SECRET_KEY<br>
 - AWS_SQS_URI<br>
 
b) You have to create posgreSQl Db<br>
 - DB_PASSWORD<br>
 - DB_URL<br>
 - DB_USER_NAME<br>
 
c) In order for a user to be able to use the bot, you will need to provide them with a token that will be sent to you via email
 - MAIL_PASSWORD
 - MAIL_USER_NAME
 - TELEGRAM_ADMIN_EMAIL (where information about new registered users will be sent)

d) Create your own telegram bot with [Bot Father](https://t.me/BotFather)
 - TELEGRAM_BOT_TOKEN<br>
 - TELEGRAM_BOT_USER_NAME (Telegram bot's username)<br>
 - TELEGRAM_HELP_EMAIL (your Telegram account's email address)<br>
 
e) Use port 5050<br>
 - PORT=5050<br>
 
f) Adjust Ngrok to convert http to https<br>
 - NGROK_TOKEN (the Telegram bot works on webhooks and only supports the HTTPS protocol, so you need to [register for ngrok](https://ngrok.com/) (free))
 
4. Run docker-compose.yml file

<h3>Remark<h3>
 
For proper functioning and the ability to send requests for parsing OLX pages, you need to configure [parser instances](https://github.com/sich-mykhailo/parser). Otherwise, the requests will be stored in the SQS queue until at least one parser instance is started.
