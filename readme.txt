
# Prelozi a spusti aplikaci s vizualizerem
javac -cp .:ijatool.jar -d build ija/ija2022/homework2/Homework2.java 
java -cp ./build:ijatool.jar ija.ija2022.homework2.Homework2

# Prelozi a spusti testy
#javac -cp .:ijatool.jar:junit-platform-console-standalone-1.6.0.jar -d build ija/ija2022/homework2/Homework2Test.java 
#java -cp ./build:ijatool.jar:junit-platform-console-standalone-1.6.0.jar org.junit.runner.JUnitCore ija.ija2022.homework2.Homework2Test
