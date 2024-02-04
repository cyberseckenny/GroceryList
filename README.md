# Grocery++

## Inspiration
Shopping for groceries can be a painstaking process, especially for a college student. Not only is it a struggle to search for recipes and gather all the ingredients, but it's even more difficult to find healthy meal options. We created Grocery++ to solve these issues. 

## What it does
As a mobile application leveraging OpenAI's API, Grocery++ generates grocery lists and dietary advice for users after they answer a series of questions. This information is custom-curated for each user and takes into account factors such as dietary restrictions, eating schedule, and physical activity level. Upon generating this information, Grocery++ will store the grocery list within the app along with general dietary advice for the user.

## How we built it
Our project was created entirely using Android Studio, which is a JetBrains-based IDE to develop Android applications. We used a library called OpenAI-Java, which enables the usage of OpenAI's API through Java.

## Challenges we ran into
Setting up the grocery list in our application proved to be a tremendous challenge due to our lack of knowledge about the MVC (Model View Controller) architecture pattern. We also ran into many unexpected compilation errors and dependency issues, which slowed down the production of our application. In the end, we were able to overcome these issues and learn much about the technologies we utilized.

## Accomplishments that we're proud of
The completion of our splash screen, which displays when you open the app, was an amazing success, as it's the most polished and eye-catching part of our application. Our project's backend also ended up being a success and was able to smoothly generate dietary advice and grocery lists when given a set of inputs from the user.

## What we learned
Overall, we learned that ~~Java sucks~~ learning an entirely new set of development tools in 36 hours is _hard_. We were also amazed to see how much work you can get done in a short, but focused, period of time.

## What's next for Grocery++?
In the future, we plan to expand Grocery++'s grocery list functionality so that items on the list can be regenerated based on the user's changing needs. To better aid the generation of grocery lists for users, we also plan to integrate the Google Maps API into our application to provide a dynamic selection of food options based on available stores. Looking at the ethicality of our product, we seek to hire a professional dietician or nutritionist to verify that the advice being produced by the AI is trustworthy and correct.
