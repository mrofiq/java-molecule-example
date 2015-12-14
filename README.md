[![Travis Build Status](https://travis-ci.org/yosethegame/java-molecule-example.svg?branch=master)](https://travis-ci.org/yosethegame/java-molecule-example)

<div id="readme">YoseTheGame</div>

java-molecule-example
=====================

A an example to get you started to Yose the Game using [Molecule](http://vtence.com/molecule).

It passes the first two challenges.

## Get ready

[Fork](https://help.github.com/articles/fork-a-repo) me and [clone](https://help.github.com/articles/fork-a-repo#step-2-clone-your-fork) your repository locally.

You will need Java 8 and [Gradle](http://www.gradle.org) to run this example.

Once you have gradle installed, build the application by running:

```sh
gradle
```

It will compile the application and run all the tests.

## Run the application

Simply run:

```sh
gradle run
```

... open your browser and navigate to:

```sh
http://localhost:8080/ping
```

If you want to change the server port:

```sh
gradle run -Pport=9000
```

## Deploy

To play the game, you will have to deploy the game to a server accessible from the game server.

If you want to try out the [Heroku Cloud Plarform](http://www.heroku.com), I have included the ```Procfile``` 
and ```system.properties``` files required by Heroku. 

I also set up the ```gradle stage``` command for you. 

You should be able to deploy the application with a simple ```git push``` to Heroku

