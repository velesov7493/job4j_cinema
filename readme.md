[![Build Status](https://app.travis-ci.com/velesov7493/job4j_cinema.svg?branch=master)](https://app.travis-ci.com/velesov7493/job4j_cinema)
## Описание ##
Это учебный проект, иллюстрирующий процесс бронирования мест на фильм в кинотеатре.
Пользователь может забронировать себе место, выполнив 3 простых действия:
+ Выбрать фильм
+ Выбрать свободное место в зале
+ Ввести свои учетные данные на странице "Оплата"

Занятые места на странице кинозала обновляются ajax-запросом каждые 10 секунд, однако
все равно существует возможность выбрать место, которое уже занято. Если такое случается,
то пользователь получает сообщение об ошибке с предложением выбрать другое место.

#### В проекте используются технологии ####
![badge](https://img.shields.io/badge/PostgreSQL-9.5-blue)
![badge](https://img.shields.io/badge/Tomcat-9.0-blue)
![badge](https://img.shields.io/badge/Java-14-green)
![badge](https://img.shields.io/badge/Maven-3.3-yellow)
![badge](https://img.shields.io/badge/jQuery-3.6-yellow)
![badge](https://img.shields.io/badge/Bootstrap-4.0-green)
## Скриншоты страниц ##
#### Выбор фильма ####
![screenshot](images/screenshoot001.png)
#### Выбор места в зале ####
![screenshot](images/screenshoot002.png)
Выбранное место в оранжевой рамке, а занятые - в фиолетовой.
#### Выбор места в зале с уже забронированными местами ####
![screenshot](images/screenshoot004.png)
#### "Оплата" ####
![screenshot](images/screenshoot003.png)
#### Если место уже забронировано ####
![screenshot](images/screenshoot005.png)