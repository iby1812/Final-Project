# CINTIX - Movie Showtimes & Tickets
## Android Application for Ariel University Final Project.

## 1. Overview
CINTIX is a mobile movie ticketing app that lets you buy movie tickets, make movie plans with friends and get a VIP experience at the movie theater. Find all the latest new and upcoming movies, watch movie trailers, read reviews and browse movie showtimes directly from the app. 
The application will do the job for you, choose the most recommended movies that suit you and select the best seats remaining in the hall automatically.

## 2. Functionality
- Get movie tickets ahead of time & reserve your seats with a few easy clicks. Then just walk right into the theater.
- Our intuitive tile layout makes it easy to find what you want.
- Get the top movies for you without unnecessary searches by **Machine Learning**.
- Automatically select seats using our **Algorithm**.

## 3. Uniqueness
### TF-IDF for Machine Learning (Our search efficiency solution)
In information retrieval, tf–idf or TFIDF, short for term frequency–inverse document frequency, is a numerical statistic that is intended to reflect how important a word is to a document in a collection or corpus. It is often used as a weighting factor in searches of information retrieval, text mining, and user modeling. The tf–idf value increases proportionally to the number of times a word appears in the document and is offset by the number of documents in the corpus that contain the word, which helps to adjust for the fact that some words appear more frequently in general. Tf–idf is one of the most popular term-weighting schemes today; 83% of text-based recommender systems in digital libraries use tf–idf.

We created Al feature that will learn your favorites movies after each movie order.
AI algorithm will fetch the movie summary and analyze it by key words. when you choose smart order, the algorithm will present three top movies that you most like by compare the key words with all other movies summarises.


### Algorithm for automatically select seats
Who wouldn't want to get the best seats available automatically while ordering movie tickets.
CINTIX seats algorithm select for you the best seats in the cinema automatically without waste your time,  tickets type and number of seats been considered as well.
The algorithm calculates for each seat the horizontal and vertical viewing angle  by distance from the screen.

Using a calculation between the viewing angles and data we collected regarding viewing and seating recommendations, we created a complete mapping of the entire hall.
The user will always be able to get the optimal 
seat available.

## 4. Features
### Login
Sign in with Facebook, saving your favorites movies for AI experience.
CINTIX would know you better and provide the most easy fast and comfort ordering.
saving app progress with facebook user in database.

<img height="300" src="https://github.com/iby1812/Final-Project/blob/master/Images/Main_page.png" />

### 90th minute
For the spontanees among us:
Last minute order feature, present for you filtered movies by displaying time up to 30 minutes and nearest cinemas by using phone location. 
Using automataclly seats selection algorithm for fast ordering

<img height="300" src="https://github.com/iby1812/Final-Project/blob/master/Images/Movie_page.png" />


## 5. Authors
[Sagi Saada](https://github.com/sagikandleker), [Itay Bar](https://github.com/iby1812) & [Roni Gueta](https://github.com/ronigu24).
