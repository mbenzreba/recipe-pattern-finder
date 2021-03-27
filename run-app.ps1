
# Ensure this is copying the most recent version of recipe-pattern-finder
Copy-Item ".\target\recipe-pattern-finder-0.6.jar" -Destination ".\testenv\"


# Run the JAR
java -cp ".\testenv\recipe-pattern-finder-0.6.jar;C:\Users\mbenz\.m2\repository\org\apache\opennlp\opennlp-tools\1.9.3\opennlp-tools-1.9.3.jar" com.mbenzreba.RecipePatternFinder.App