package ru.nsu.fit.dib.projectdib;

import java.awt.*;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.onKey;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class App extends GameApplication {
  Factory factory;
  Viewport viewport;
  Config cfg;
  private Entity player;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  protected void initSettings(GameSettings settings) {
    Config.setConfig("src/main/resources/cfg.ini");
    // Window mod
    switch (Config.WINDOW_MODE) {
      case "Window":
        {
          settings.setWidth(Config.WINDOW_WIDTH);
          settings.setHeight(Config.WINDOW_HEIGHT);
          break;
        }
      case "Borderless":
        {
          System.out.print(Config.WINDOW_MODE);
          Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
          int UserHeight = dimension.height;
          int UserWidth = dimension.width;
          settings.setHeight(UserHeight);
          settings.setWidth(UserWidth);
          break;
        }
      case "Full-screen":
        {
          settings.setWidth(Config.WINDOW_WIDTH);
          settings.setHeight(Config.WINDOW_HEIGHT);
          settings.setFullScreenAllowed(true);
          settings.setFullScreenFromStart(true);
          break;
        }
    }
    // Title
    settings.setTitle("DiB");
  }
  // Управление
  @Override
  protected void initInput() {
    onKey(KeyCode.W, () -> player.translateY(-2));
    onKey(KeyCode.S, () -> player.translateY(2));
    onKey(KeyCode.A, () -> player.translateX(-2));
    onKey(KeyCode.D, () -> player.translateX(2));
  }

  // Спавн существ
  @Override
  protected void initGame() {
    viewport = getGameScene().getViewport();
    factory = new Factory();

    getGameWorld().addEntityFactory(factory);
    FXGL.setLevelFromMap("tmx/exlevel2.tmx");

    this.player = spawn("player", getAppWidth() / 2 - 15, getAppHeight() / 2 - 15);
    viewport.bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
  }
}
