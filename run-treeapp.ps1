
# Ensure this is copying the most recent version of recipe-pattern-finder
Copy-Item ".\target\recipe-pattern-finder-0.3-SNAPSHOT.jar" -Destination ".\testenv\"


# Run the JAR
java -cp .\testenv\recipe-pattern-finder-0.3-SNAPSHOT.jar com.mbenzreba.RecipePatternFinder.TreeApp