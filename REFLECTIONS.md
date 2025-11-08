# Reflections L3

## Chapter 2

Chapter 2 of Clean Code is all about creating meaningful names and the importance of good naming conventions. This is, with out a doubt one of the major topics regarding code quality that has affected me. I consider every name I create, dont get me wrong, before i read this book and took this course i had issues coming up with names at all, but thats not the case now. Now the creation of names have become quite easy but the part that requires consideration is `How to get the name as intention revealing and pronouncable as possible` instead. So to wrap this up, naming conventions affect all of my code, from start to finish, to maintenance. 

Examples:
---
![Example1](./img/Chapter2Naming1.png)
---
![Example2](./img/Chapter2Naming2.png)   
---
![Example3](./img/Chapter2Naming3.png)
---

## Chapter 3

Regarding the rules for funcitons I do agree with them to an extent. For example, `Do one thing` & `One level of abstraction per function`. These two rules are not always appropriate to implement according to me and should be used as a guide line and be strictly applied where possible without creating messy code. Id argue that code can also become a mess with to many well named functions creating an un-ending series of well named functions that you have to follow.

Other rules that affect my way of writing functions are the `DRY` principle, `Prefer Exceptions to Returning Error Codes` and `Small!`, the first two mentioned rules are self explanatory and the code should for the most part adhere to these rules. When it comes to keeping functions small, I try to, but it is simply not always possible to keep them as small as the litterature instructs with out making the code messy, as i mentioned earlier. Code can become messy in more then one way.

When it comes to function arguments, my goal is always to keep as many functions as possible niladic or monadic. It is not always simple to get the code base to that stage on the first try, so there are several occurances of dyadic functions and one single triadic in EncryptionService. Im sure that some consideration and refactoring could break out several of the dyadic functions to monadic.

Examples:

`A short, self explanatory function that does more then one thing.`

![Example1](./img/Chapter3Functions1.png)
---
`Niladic functions doing one or arguably two things`

![Example2](./img/Chapter3Functions2.png)
---
`The one triadic function, this should probably be refactored.`

![Example3](./img/Chapter3Functions3.png)
---

## Chapter 4

_"The proper use of comments is to compensate for our failure to express ourself in code."_ - Clean Code, Chapter 4

_"Comments are, at best, a necessary evil."_ - Clean Code, Chapter 4

All the rules about comments really do come back to one conclusion, dont comment unless absolutely needed. Coming from the view that everything should be documented with line comments, javaDoc/jsDoc alongside extensive markdown files, this was a big and hard-to-understand change. 
Now, I completely agree with the concept of not using comments. I always start my developing by over-commenting everything i might need clarification on or might forget about. As the project goes along i will start to change the comments and eventually remove all comments that you can read out from the code it is connected to. The commentary I do like to keep are an introductory Doc comment within each module with a short one line explanation and possibly more info that is necessary. The second type of commentary are, simply, comments that simplify or clarify things I think needs a bit of extra context.

`JavaDoc used at the top of each module.`

![Example1](./img/Chapter4Comments1.png)
---

`Javadoc for a larger function and line comment to explain a regex validation.`

![Example2](./img/Chapter4Comments2.png)
---
## Chapter 5

Not counting the main module there are six class modules in this project, currently. Three of them are around 100 lines of code, excluding empty rows and comments, those are the large modules. 
That said, I do appreciate and the weight of proper formatting in a project but I have not focused much energy on formatting this project after a certain pattern, mostly because im still learning and simply forgot to plan ahead and create a consistent formatting strategy.

Usually i prefer to start a class with the constructor, followed by setters (with attached validation functions) and then getters either after the setters or at the very bottom. 
Then I usually reason to put the different functions in the order they are used by the system for a normative use case(Procedural?).

The realization I come to now is that I probably think about formatting quite alot, without connecting it to the formatting studies from Clean Code.

`AI estimate of lines of code in each module. `

![Example1](./img/Chapter5Formatting1.png)
---

## Chapter 6

`Data Abstraction` and `Law of Demeter`, the object oriented way, which comes quite naturally when using Java. To keep it short, this chapter has served to enhance and repeat information from a previous course but in conjunction with the concepts of Clean Code. So id say that it have not affected the way I wrote this code directly, more indirectly and serve as well needed repetition for further improved knowledge of OOP. 

`Example usage of members and setters.`

![Example1](./img/Chapter6Objects1.png)
---

`Private members.`

![Example2](./img/Chapter6Objects2.png)
---

## Chapter 7

When it comes to error handling I've mainly tried to follow three of the principles; `Use exceptions rather than return codes`, `Provide context with exceptions` & avoiding null, wether it is returning or passing. This is how i have been doing it previously aswell except for the insight about the usage of null. Moving forward i will try to learn the TDD approach alongside practicing to implement custom exceptions. I noticed upon going through the code that i need to work on my usage of the try/catch/finally logic, it is a bit scattered and could probably use some more thought.

`Usage of try/catch`

![Example1](./img/Chapter7Error1.png)
---

`Throwing excepetion with descriptive information.`

![Example2](./img/Chapter7Error2.png)
---

## Chapter 8

My understanding of the chapter about boundaries is that you should keep external as isolated from the rest of the source code as possible, `Clean Boundaries`. In my application, where my external encryption library is used, it is kept to a single class called EncryptionUtil which in turn offers an "interface" that EncryptionService can work with.

`Where the eternal library exists in my application.`

![Example1](./img/Chapter8Boundaries1.png)
---

`Example of EncryptionService using the interface to the external code.`

![Example2](./img/Chapter8Boundaries2.png)
---

## Chapter 9

`Clean Tests`, `Domain-specific Testing Language`, `Single Concept per Test` and `F.I.R.S.T` are the "rules" I've been trying to follow through the process of creating the tests. The most noticeable impact that clean code have had on how I create the automated testing is naming, readability. 
Though my testing could be refactored into several more tests, I can see many of them have more then one assert and do more then one thing.

`Test setup`

![](./img/Chapter9Testing4.png)
---

`Java FX automated UI-test setup`

![](./img/Chapter9Testing2.png)
---

`Test with one assertion`

![](./img/Chapter9Testing3.png)
---

`Test with multiple assertions`

![](./img/Chapter9Testing1.png)
---

`Test Naming`

![](./img/Chapter9Testing5.png)
---

## Chapter 10

`Classes should be small!`, so my classes are small, in terms of lines of code. I try to keep them focused on a certain task or in some cases, a few tasks that are related. As chapter 10 mentions a few times, the SRP design principle is one of the more important principles to adhere to. The way the book describes class organization is how I intend to keep it when I develop, there is some inconsistency in the way that I arrange my methods in a class and that is something I need to work on.

`Small Classes`

![Class size estimate](./img/Chapter5Formatting1.png)
---

`Naming and Members`

![Naming and Members](./img/Chapter10Classes1.png)
---

## Chapter 11

When it comes to system-level design principles from Chapter 11, I've partially applied some concepts but not consciously focused on them.
Regarding `Domain-Specific Language`, I've tried to use domain-specific method names like `encryptAndSave()`, `decrypt()`, and `getAllEntries()` that reflect the encryption/storage domain rather than generic technical terms. The MVC pattern I've used helps with system organization, keeping the view separate from business logic.
`System Architecture - Separation of Main` is partially achieved - my `main()` method is separate and only calls `launch()`, but the actual construction happens in `start()`. For a small application like this, the current approach works, but for larger systems, I would benefit from a more explicit separation between startup/configuration and runtime logic.

`Dependency Injection Example`

![Example1](./img/Chapter11Systems3.png)![Example2](./img/Chapter11Systems4.png)

`Construction mixed with use`

![Example3](./img/Chapter11Systems1.png)

`Domain-Specific Language in method names`

![Example4](./img/Chapter11Systems2.png)

# Reflections Module

## Refactoring

Images and comments regarding the refactoring of the module(L2).

### EncryptionCipher.java

This class has gone through a major refactoring: 
- validation has been lifted from the actual encryption and decryption methods. 
- the different ways to handle the shifting of characters have also been refactored to separate methods. 
- Renaming. 
- Most comments have been removed. 
- A private, unchangable member attribute have been introduced to reduce method responsibility. 
- Some rearranging of the code blocks have been made to improve the formatting. 

This Class was not poorly structured but it was far from followin Clean Code regulations, now, several improvements have been made to take it alot closer to being "Clean Code". 

`Before Refactoring`

![EncryptionCipherBefore](./img/moduleRefactor/encryptionCipherBefore.png)
---
---

`After Refactoring`

![EncryptionCipherAfter](./img/moduleRefactor/encryptionCipherAfter.png)
---
---

### Salt.java

This class has gone through a major refactoring:
- Validation have been refactored to its own methods/functions and then called where needed.
- The add and remove salt methods have been refactored by moving logic to separate methods/functions.
- Most comments were removed.
- Unused code removed.

`Before Refactoring`

![SaltBefore](./img/moduleRefactor/SaltBefore.png)
---
---

`After Refactoring`

![SaltAfter](./img/moduleRefactor/SaltAfter.png)
---
---

### Encryption.java

This class has gone through a minor refactoring:
- Variables were inconsistent(local, instance, magic.) and spread out, refactored to member attributes.
- Reduced amount of comments.

`Before Refactoring`

![EncryptionBefore](./img/moduleRefactor/EncryptionBefore.png)
---
---

`After Refactoring`

![EncryptionAfter](./img/moduleRefactor/EncryptionAfter.png)
---
---

### StringManipulator.java

This class has gone through a major refactoring:
- Remove most comments.
- Reduce some code by moving it from its own line to the return statement.
- Several methods have been refactored, creating several smaller methods.

`Before Refactoring`

![StringManipulatorBefore1](./img/moduleRefactor/StringManipulatorBefore1.png) 
---
![StringManipulatorBefore2](./img/moduleRefactor/StringManipulatorBefore2.png) 
---
![StringManipulatorBefore3](./img/moduleRefactor/StringManipulatorBefore3.png)
---
---

`After Refactoring`

![StringManipulatorAfter1](./img/moduleRefactor/StringManipulatorAfter1.png) 
---
![StringManipulatorAfter2](./img/moduleRefactor/StringManipulatorAfter2.png) 
---
![StringManipulatorAfter3](./img/moduleRefactor/StringManipulatorAfter3.png)
---
---

### StringCryption.java

This class has gone through a minor refactoring:
- Reduced comments.
- Validation lifted to separate methods.
- Switch conditionals lifted to separate methods.

`Before Refactoring`

![StringCryptionBefore1](./img/moduleRefactor/StringCryptionBefore1.png) ![StringCryptionBefore2](./img/moduleRefactor/StringCryptionBefore2.png)
---
---

`After Refactoring`

![StringCryptionAfter1](./img/moduleRefactor/StringCryptionAfter1.png) ![StringCryptionAfter2](./img/moduleRefactor/StringCryptionAfter2.png)
---
---

# Final & Personal Reflections.

Vad gäller L3 så var uppgiften 90% klar och fullt fungerande lokalt när jag pausade arbetet för ett år sedan. Jag uppfattade felaktigt att jag behövde vänta till nästa kursomgång och omregistrera mig. 
I vanlig ordning blev min idé som verkade ganska enkel att utföra, ganska omfattande och jag valde bort verktyg som maven/gradle samt att använda ny teknik för mig, JavaFX. JavaFX och dess testning var, efter en hel del "trial & error" helt okej att använda och den automatiska GUI-testningen var häftig. JavaFX står fortfarande ivägen och orsakar en del problem, som är dokumenterat.

Att refaktorera modulen var den större delen av kvarvarande arbete, det var inte svårt att hitta "fel" som behövde refaktoreras och jag har gjort mitt bästa att följa Clean Code. Jag tror att det som framför allt kan förbättras är relaterat till Formating-kapitlet i boken, det kan visa sig utmanande när man har en klass som StringManipulator.java som har flertalet olika metoder för att uppfylla sitt syfte.

