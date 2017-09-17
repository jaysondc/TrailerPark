# Project 1 - *Trailer Park*

**Name of your app** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **16** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **scroll through current movies** from the Movie Database API
* [X] Layout is optimized with the [ViewHolder](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern) pattern.
* [X] For each movie displayed, user can see the following details:
  * [X] Title, Poster Image, Overview (Portrait mode)
  * [X] Title, Backdrop Image, Overview (Landscape mode)

The following **optional** features are implemented:

* [X] Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.

The following **bonus** features are implemented:

* [ ] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [ ] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* [ ] Allow video trailers to be played in full-screen using the YouTubePlayerView.
    * [X] Overlay a play icon for videos that can be played.
    * [ ] More popular movies should start a separate activity that plays the video immediately.
    * [ ] Less popular videos rely on the detail page should show ratings and a YouTube preview.
* [X] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code. (Used [Kotlin Synthetics](https://antonioleiva.com/kotlin-android-extensions/))
* [ ] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [X] Replaced android-async-http network client with the popular [OkHttp](http://guides.codepath.com/android/Using-OkHttp) networking libraries. (Used Retrofit)

The following **additional** features are implemented:

* [X] Added trailers to the main screen in a scrollview
* [X] Implemented async network calls through RxJava and Retrofit
* [X] Implemented trailer view recycling so improve scrolling performance

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://i.imgur.com/tWao7os.gif' title='Video Walkthrough Portrait' width='300' alt='Video Walkthrough Portrait' />

<img src='https://i.imgur.com/663t30d.gif' title='Video Walkthrough Landscape' width='600' alt='Video Walkthrough Landscape' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

I've had experience implementing applications similar to this already, so I decided to challenge myself by writing it in Kotlin and using more advanced libraries like Retrofit and RxJava. RxJava in particular is tough to wrap your mind around, but once I got the hang of it, it was easy to interface with APIs through simple subscribe calls. I also implemented an adapter pattern that uses AdapterDelegates to handle multiple view types, but I never used it.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [RxJava](https://github.com/ReactiveX/RxJava) - Library for composing asynchronous and event-based programs using observable sequences for the Java VM.
- [Retrofit](http://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Kotlin](https://kotlinlang.org/) - A statically typed programming language for modern multiplatform applications

## License

    Copyright 2017 Jayson Dela Cruz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.