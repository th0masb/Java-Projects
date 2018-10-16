/**
 *
 */
package jenjinn.engine.eval.staticexchangeevaluator;

import static jflow.utilities.CollectionUtil.head;
import static jflow.utilities.CollectionUtil.last;
import static jflow.utilities.Strings.allMatches;
import static jflow.utilities.Strings.firstMatch;

import jenjinn.engine.base.Square;
import jenjinn.engine.pgn.CommonRegex;
import jflow.collections.FList;

/**
 * @author ThomasB
 *
 */
final class IndividualStateCase
{
	public final Square source, target;
	public final boolean isGoodExchange;

	public IndividualStateCase(Square source, Square target, boolean isGoodExchange)
	{
		this.source = source;
		this.target = target;
		this.isGoodExchange = isGoodExchange;
	}


	public static IndividualStateCase from(String encoded)
	{
		String sq = CommonRegex.SINGLE_SQUARE, doubleSq = CommonRegex.DOUBLE_SQUARE;
		if (encoded.matches("^" + doubleSq + " +(GOOD|BAD)$")) {
			FList<Square> sqs = allMatches(encoded, sq)
					.map(String::toUpperCase)
					.map(Square::valueOf)
					.toList();
			return new IndividualStateCase(head(sqs), last(sqs), firstMatch(encoded, "GOOD").isPresent());
		}
		else {
			throw new IllegalArgumentException(encoded);
		}
	}

	@Override
	public String toString()
	{
		return new StringBuilder("[source=")
				.append(source)
				.append(", target=")
				.append(target)
				.append(", result=")
				.append(isGoodExchange)
				.append("]")
				.toString();
	}
}
