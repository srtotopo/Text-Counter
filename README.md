# Text Counter

Text Counter is a small .NET console application that analyzes a line of text and prints:

- the total number of characters
- the number of words
- the top 3 most frequent words

## Requirements

- .NET 10 SDK

## How to run

From the project root, run:

```bash
dotnet run --project TextCounter/TextCounter.csproj
```

The app will prompt for a text value, then print the character count, word count, and the top 3 words.

## Behavior

- Character count includes spaces and punctuation.
- Word count is case-insensitive.
- Punctuation is removed before counting words.
- Words are split using whitespace.

## Tests

Run the automated tests with:

```bash
dotnet test
```
