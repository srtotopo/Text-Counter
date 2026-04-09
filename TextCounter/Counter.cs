using System.Text.RegularExpressions;

namespace TextCounter;

public class Counter
{
    private readonly SortedDictionary<string, int> _wordFrequency = new();

    public Counter(string? text)
    {
        text ??= string.Empty;

        CharacterCount = text.Length;

        var words =
            Regex.Replace(text, @"\p{P}+", "")
                .ToLowerInvariant()
                .Split((char[]?)null, StringSplitOptions.RemoveEmptyEntries);

        WordCount = words.Length;

        foreach (var word in words)
        {
            _wordFrequency.TryGetValue(word, out var count);
            _wordFrequency[word] = count + 1;
        }
    }

    public int CharacterCount { get; }
    public int WordCount { get; }

    public Dictionary<string, int> GetTop3Words()
    {
        return _wordFrequency
            .OrderByDescending(k => k.Value)
            .ThenBy(k => k.Key, StringComparer.Ordinal)
            .Take(3)
            .ToDictionary(k => k.Key, v => v.Value);
    }
}