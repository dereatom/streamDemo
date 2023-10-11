import java.util.StringJoiner;
import java.util.stream.Collector;

public class CustomCollectors {
    public static Collector<String, StringJoiner, String> concatenating() {
        return Collector.of(
                () -> new StringJoiner(", "),
                StringJoiner::add,
                StringJoiner::merge,
                StringJoiner::toString
        );
    }
}
/*
The `concatenating()` method defines a custom collector that concatenates strings using a `StringJoiner`. Let's break down the code step by step:

1. **Method Signature:**
   ```java
   public static Collector<String, StringJoiner, String> concatenating() {
   ```

   - This method is declared as `public` and `static`, making it accessible and not tied to an instance of a class.
   - It returns a `Collector` with the following parameters:
     - `<String>`: The type of elements to be collected (in this case, `String`).
     - `<StringJoiner>`: The mutable accumulation type.
     - `<String>`: The result type of the collection.

2. **`Collector.of` Method:**
   ```java
   return Collector.of(
           () -> new StringJoiner(", "),
           StringJoiner::add,
           StringJoiner::merge,
           StringJoiner::toString
   );
   ```

   - The `Collector.of` method is used to create a new collector based on the provided supplier, accumulator, combiner, and finisher functions.
   - The arguments passed to `Collector.of` define how the collector behaves during the accumulation and merging steps.

3. **Supplier (`() -> new StringJoiner(", ")`):**
   - The supplier provides an initial value for the accumulation. In this case, it creates a new `StringJoiner` instance with a comma and space (", ") as the delimiter.

4. **Accumulator (`StringJoiner::add`):**
   - The accumulator is a function that takes a mutable result container (`StringJoiner`) and an input element (`String`) and incorporates the element into the result.
   - Here, it uses `StringJoiner::add` to add the input string to the `StringJoiner`.

5. **Combiner (`StringJoiner::merge`):**
   - The combiner function is used to merge two partial results (accumulators) into one.
   - Here, it uses `StringJoiner::merge` to merge two `StringJoiner` instances.

6. **Finisher (`StringJoiner::toString`):**
   - The finisher function is called to transform the result container (`StringJoiner`) into the final result (in this case, a `String`).
   - Here, it uses `StringJoiner::toString` to obtain the concatenated string from the `StringJoiner`.

In summary, the `concatenating()` method defines a custom collector that uses a `StringJoiner` to concatenate strings with a specified delimiter (", "). It specifies how to accumulate, merge, and finish the collection of strings into a single concatenated string.
*/

