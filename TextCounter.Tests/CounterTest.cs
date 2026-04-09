using JetBrains.Annotations;
using Xunit;

namespace TextCounter.Tests;

[TestSubject(typeof(Counter))]
public class CounterTest
{
    [Fact]
    public void CharacterCount_ReturnsLength()
    {
        var counter = new Counter("hola mundo!");

        Assert.Equal(11, counter.CharacterCount);
    }

    [Fact]
    public void WordCount_Empty_ReturnsZero()
    {
        var counter = new Counter(string.Empty);

        Assert.Equal(0, counter.WordCount);
    }

    [Fact]
    public void WordCount_OnlySpaces_ReturnsZero()
    {
        var counter = new Counter("   ");

        Assert.Equal(0, counter.WordCount);
    }

    [Fact]
    public void WordCount_MultipleSeparators_CountsWords()
    {
        var counter = new Counter("uno\tdos\n tres   cuatro");

        Assert.Equal(4, counter.WordCount);
    }

    [Fact]
    public void WordCount_RemovesPunctuation()
    {
        var counter = new Counter("Hola, mundo! esto? sí.");

        Assert.Equal(4, counter.WordCount);
    }

    [Fact]
    public void Top3Words_ReturnsTopThree()
    {
        var counter = new Counter("uno dos dos tres tres tres cuatro cuatro cuatro cuatro");

        var topWords = counter.GetTop3Words();

        Assert.Equal(3, topWords.Count);
        Assert.Equal(4, topWords["cuatro"]);
        Assert.Equal(3, topWords["tres"]);
        Assert.Equal(2, topWords["dos"]);
    }

    [Fact]
    public void Top3Words_LessThanThree_ReturnsAvailable()
    {
        var counter = new Counter("gato perro perro");

        var topWords = counter.GetTop3Words();

        Assert.Equal(2, topWords.Count);
        Assert.Equal(2, topWords["perro"]);
        Assert.Equal(1, topWords["gato"]);
    }

    [Fact]
    public void Top3Words_IsCaseInsensitive()
    {
        var counter = new Counter("Hola, hola! HOLA?? adios.");

        var topWords = counter.GetTop3Words();

        Assert.Equal(2, topWords.Count);
        Assert.Equal(3, topWords["hola"]);
        Assert.Equal(1, topWords["adios"]);
    }

    [Fact]
    public void Top3Words_Empty_ReturnsEmpty()
    {
        var counter = new Counter(string.Empty);

        var topWords = counter.GetTop3Words();

        Assert.Empty(topWords);
    }
}