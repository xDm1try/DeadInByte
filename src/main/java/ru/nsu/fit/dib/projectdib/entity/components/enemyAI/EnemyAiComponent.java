package ru.nsu.fit.dib.projectdib.entity.components.enemyAI;

import static com.almasb.fxgl.core.math.FXGLMath.sqrt;
import static com.almasb.fxgl.dsl.FXGL.geto;
import static ru.nsu.fit.dib.projectdib.data.ProjectConfig.rangeOfVision;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.pathfinding.astar.AStarCell;
import com.almasb.fxgl.pathfinding.astar.AStarPathfinder;
import com.google.javascript.jscomp.jarjar.javax.annotation.CheckForNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.geometry.Point2D;
import ru.nsu.fit.dib.projectdib.EntityType;
import ru.nsu.fit.dib.projectdib.entity.components.PlayerChaseComponent;
import ru.nsu.fit.dib.projectdib.entity.components.view.HeroViewComponent;
import ru.nsu.fit.dib.projectdib.entity.weapons.WeaponViewComponent;
import ru.nsu.fit.dib.projectdib.newMultiplayer.context.client.MCClient;

public class EnemyAiComponent extends Component {
  LazyValue<AStarPathfinder> pathfinder = new LazyValue<>(() -> new AStarPathfinder(geto("grid")));
  //AStarMoveComponent aStar = new AStarMoveComponent(new LazyValue<>(() -> geto("grid")));
  //AStarPathfinder path = new AStarPathfinder(geto("grid"));
  AStar aStar = new AStar(new LazyValue<>(() -> geto("grid")));
  private Map<Integer, Entity> gameMapOfEntities = MCClient.getClientState().getIdHashTable();
  private Entity currentEnemy;
  private List<Entity> heroList;
  private List<Point2D> memoryOfLastHeroesPositions;

  PlayerChaseComponent chase;

  @Override
  public void onAdded() {
    currentEnemy = getEntity();
    currentEnemy.addComponent(aStar);
    //currentEnemy.addComponent(aStar);
    //chase = new PlayerChaseComponent(aStar);
  }

  @Override
  public void onUpdate(double tpf) {
    Entity target = findNearestHero();
    if (target == null) {
      System.out.println("DOES NOT SEE");
      currentEnemy.getComponent(AStar.class).stopMovement();
    }else{
      currentEnemy.getComponent(AStar.class).moveToCell(target.call("getCellX"), target.call("getCellY"));
    }
  }

  @CheckForNull
  private Entity findNearestHero() {
    List<Entity> heroList = FXGL.getGameWorld().getEntitiesByType(EntityType.PLAYER);
//        gameMapOfEntities.values().stream()
//            .filter(entities -> entities.getType()== EntityType.PLAYER)
//      //      .filter(heroes -> distanceBetweenEntities(currentEnemy, heroes) < 500000000)
//            .toList();

    heroList = heroList.stream()
        .filter(
            entity -> {
              double dist = entity.distance(currentEnemy);
              System.out.println("DISTANCE = "+ dist);
              return dist <= rangeOfVision;
            }).toList();
    if (heroList.isEmpty()) {
      return null;
    }

    double nearestHeroDist = Double.MAX_VALUE;
    Entity nearestHero = heroList.get(0);

    for (Entity hr : heroList) {
      double dist =
          sqrt(
              hr.getPosition().getX() * hr.getPosition().getX()
                  + hr.getPosition().getY() * hr.getPosition().getY());
      if (dist < nearestHeroDist) {
        nearestHero = hr;
        nearestHeroDist = dist;
      }
    }
    return nearestHero;
  }
}
