# README [WAŻNE / IMPORTANT]
[:poland:] Dziękuję bardzo za zaproszenie do procesu rekrutacyjnego. Powyższy projekt jest rozwiązaniem zadania o blokach i ścianie.

[:uk:] Thank you for invitation to recruitment process. Project above is an solution to exercise about blocks and wall.

## Instalacja / Instalation

```
$ git clone https://github.com/MemeeMaster/HorusRecruitmentProject
$ cd HorusRecruitmentProject
```
## Działanie / How it works
[:poland:] Sprawdzenie czy wszystkie funkcjonalności działają poprawnie zawarłem w testach napisanych przy użyciu JUnit5. Żeby uruchomić testy należy najczęściej użyć po prostu skrótu klawiszowego: **``Ctrl + Shift + F10``** dla Windows lub **``Control + Shift + R``** dla MacOS lub użyć komendy **``mvn test``** w terminalu.

[:uk:] To check if all the functionalities are working correctly, I included tests written using JUnit5. To run the tests, most of the time you should simply use a keyboard shortcut:  **``Ctrl + Shift + F10``** for Windows or **``Control + Shift + R``** for MacOS or use command **``mvn test``** in your terminal.

## Wyjaśnienie rozwiązania / Solution explanation
[:poland:] Podczas analizy polecenia napotkałem problem, dotyczący niejasnego określenia czym jest **"element"** ściany. Nie wiedziałem czy podczas podliczania wszystkich elementów ściany metodą ``count`` jako element powinienem liczyć:
* Tylko bloki pojedyncze, czyli bloki korzystające z interfejsu Block oraz bloki będące składowymi CompositeBlock,
* Bloki pojedyncze oraz CompositeBlock jako jeden element,
* Bloki pojedyncze, CompositeBlock jako jeden element oraz jego składowe będące kolejno nowymi elementami.

Wybrałem opcję trzecią gdyż wydaje mi się ona najbardziej uniwersalna, choć prawidłowa mogła być też opcja pierwsza. W mojej implementacji metoda count zlicza zarówno pojedyncze bloki, CompositeBlocki jako całość oraz jego składowe, czyli przykładowo jeśli ściana składa się z **dwóch** bloków pojedynczych oraz **jednego** CompositeBlocka składającego się z **trzech** kolejnych pojedynczych bloków to metoda zwróci wynik 6.
```
2 (Block) + 1 (CompositeBlock jako całość) + 3 (składowe CompositeBlocka) = 6
```

Dodatkowo gdyby wymaganą implementacją okazał się scenariusz pierwszy to w implementacji dodałem komentarze, które pomogą zmienić kod tak aby działał prawidłowo. Komentarzy tych można szukać w stworzonej przeze mnie funkcji ``extractCompositeBlock`` która dodatkowo usunie CompositeBlock jako całość z listy, oraz w testach które przestaną podliczać CompositeBlock jako element i sprawdzać koloru/materiału dla niego. 

<hr/>

[:uk:] While analyzing an excercise, I encountered a problem regarding the unclear definition of what **'element'** of the wall is. I did not know whether, when subcounting all the elements of a wall using the ``count`` method, I should count as an element:
* Only single blocks, i.e. blocks that use the Block interface, and blocks that are components of a CompositeBlock,
* Single blocks and CompositeBlock as one element,
* Single blocks, CompositeBlock as a single element and its components that are successively new elements.

I chose the third option because it seems to me to be the most universal, although the first option could also be correct. In my implementation, the count method counts both single blocks, the CompositeBlock as a whole and its components, that is, for example, if a wall consists of **two** single blocks and **one** CompositeBlock consisting of **three** single blocks then the method will return a result of 6.
```
2 (Block) + 1 (CompositeBlock as a whole) + 3 (CompositeBlock components) = 6
```

In addition, if the required implementation turn out to be scenario one, I have added comments in the implementation to help change the code required for project to work correctly. You can look for these comments in the ``extractCompositeBlock`` function I created, which will additionally remove the CompositeBlock as a whole from the list, and in the tests that will stop counting the CompositeBlock as an element and checking the color/material for it. 

## Struktura projektu / Project structure
[:poland:] Rozbiłem początkowy plik na mniejsze pliki aby poprawić czytelność kodu. Opisane elementy programu możesz znaleźć w drzewku poniżej.

[:uk:] I broke the initial file into smaller files to improve the readability of the code. You can find the described program elements in the tree down below.
```
├───src
│   ├───main
│   │   └───java
│   │       └───org
│   │           └───example				// klasy / classes
│   │               └───interfaces		// interfejsy / interfaces
│   └───test
│       └───java
│           └───org
│               └───example				// testy / tests
```
## Dziękuję! / Thank You!
[:poland:] Dziękuję za daną szansę, naprawdę świetnie się bawiłem przy wykonaniu tego projektu. Pozdrawiam i życzę udanego dnia!

[:uk:] Thank you for the given chance. I really had a great time doing this project. Best regards and have a great day!
