using TextCounter;

Console.WriteLine("Enter a text: ");
var text = Console.ReadLine();

var counter = new Counter(text ?? string.Empty);

Console.WriteLine("\nCharacter count: " + counter.CharacterCount);
Console.WriteLine("Words count: " + counter.WordCount);
Console.WriteLine("\nTop 3 most used words:");

foreach (var word in counter.GetTop3Words()) Console.WriteLine($"- {word.Key}: {word.Value}");