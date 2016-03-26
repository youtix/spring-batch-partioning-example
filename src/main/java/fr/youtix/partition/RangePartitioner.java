package fr.youtix.partition;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class RangePartitioner implements Partitioner {

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {

		Map<String, ExecutionContext> result = new HashMap<>();

		int range = 10;
		int fromId = 1;
		int toId = range;

		for (int i = 1; i <= gridSize; i++) {
			ExecutionContext value = new ExecutionContext();

			value.putInt("fromId", fromId);
			value.putInt("toId", toId);

			result.put("partition" + i, value);

			fromId = toId + 1;
			toId += range;

		}

		return result;
	}

}
