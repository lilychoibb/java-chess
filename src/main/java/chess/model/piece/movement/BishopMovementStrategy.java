package chess.model.piece.movement;

import chess.model.piece.Direction;
import chess.model.position.Distance;
import java.util.List;

public final class BishopMovementStrategy implements MovementStrategy {

    private static class MovementHolder {
        private static final MovementStrategy MOVEMENT = new BishopMovementStrategy();
    }

    private static final List<Direction> DIRECTIONS = Direction.DIAGONAL;

    private BishopMovementStrategy() {
    }

    public static MovementStrategy getInstance() {
        return MovementHolder.MOVEMENT;
    }

    @Override
    public boolean movable(final Distance distance, final AttackEvaluator attackEvaluator) {
        return attackEvaluator.isAttackAble() && isAvailableDirection(distance);
    }

    private boolean isAvailableDirection(final Distance distance) {
        return DIRECTIONS.stream()
                .anyMatch(distance::matchByDirection);
    }
}
