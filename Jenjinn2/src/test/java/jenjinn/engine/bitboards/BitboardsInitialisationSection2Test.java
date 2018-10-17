/**
 *
 */
package jenjinn.engine.bitboards;

import static java.util.Arrays.asList;
import static jenjinn.engine.bitboards.BitboardsInitialisationSection2.calculateOccupancyVariations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import jenjinn.engine.base.Dir;
import jenjinn.engine.base.Square;
import jflow.iterators.factories.Iter;

/**
 * @author ThomasB
 */
class BitboardsInitialisationSection2Test
{
	@ParameterizedTest
	@MethodSource
	void testFindAllPossibleOrCombos(long[] input, Set<Long> expectedResult)
	{
		assertEquals(expectedResult, Iter.overLongs(BitboardsInitialisationSection2.foldedPowerset(input)).boxed().toSet());
	}

	static Stream<Arguments> testFindAllPossibleOrCombos()
	{
		return Stream.of(
				Arguments.of(new long[0], new HashSet<>(asList(0L))),
				Arguments.of(new long[] {4L}, new HashSet<>(asList(0L, 4L))),
				Arguments.of(new long[] {0b1L, 0b1010L}, new HashSet<>(asList(0L, 0b1L, 0b1010L, 0b1011L)))
				);
	}


	@ParameterizedTest
	@MethodSource
	void testCalculateOccupancyVariations(Set<Long> expectedResult, Square startSquare, List<Dir> movementDirections)
	{
		assertEquals(expectedResult, Iter.overLongs(calculateOccupancyVariations(startSquare, movementDirections)).boxed().toSet());
	}

	static Stream<Arguments> testCalculateOccupancyVariations()
	{
		Arguments firstCase = Arguments.of(
				new HashSet<>(asList(0L, 0b1000000000L)),
				Square.F2,
				asList(Dir.E, Dir.S)
				);

		Arguments secondCase = Arguments.of(
				new HashSet<>(asList(0L, 0b10L, 0b100L, 0b1000L, 0b110L, 0b1010L, 0b1100L, 0b1110L)),
				Square.D1,
				asList(Dir.E)
				);

		return Stream.of(firstCase, secondCase);
	}
}
