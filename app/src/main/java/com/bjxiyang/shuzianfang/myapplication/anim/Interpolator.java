package com.bjxiyang.shuzianfang.myapplication.anim;



public class Interpolator implements android.view.animation.Interpolator {
  @Override
  public float getInterpolation(float input) {
    return getElasticInOut(input, 1, 0.45);
  }
  private static float getBounceOut(float elapsedTimeRate) {
    if (elapsedTimeRate < 1 / 2.75) {
      return (float) (7.5625 * elapsedTimeRate * elapsedTimeRate);
    } else if (elapsedTimeRate < 2 / 2.75) {
      return (float) (7.5625 * (elapsedTimeRate -= 1.5 / 2.75) * elapsedTimeRate + 0.75);
    } else if (elapsedTimeRate < 2.5 / 2.75) {
      return (float) (7.5625 * (elapsedTimeRate -= 2.25 / 2.75) * elapsedTimeRate + 0.9375);
    } else {
      return (float) (7.5625 * (elapsedTimeRate -= 2.625 / 2.75) * elapsedTimeRate + 0.984375);
    }
  }
  private static float getBounceIn(float elapsedTimeRate) {
    return 1f - getBounceOut(1f - elapsedTimeRate);
  }
  private static float getElasticIn(float elapsedTimeRate, double amplitude, double period) {
    if (elapsedTimeRate == 0 || elapsedTimeRate == 1) return elapsedTimeRate;
    double pi2 = Math.PI * 2;
    double s = period / pi2 * Math.asin(1 / amplitude);
    return (float) -(amplitude * Math.pow(2f, 10f * (elapsedTimeRate -= 1f)) * Math.sin((elapsedTimeRate - s) * pi2 / period));
  }
  private static float getElasticInOut(float elapsedTimeRate, double amplitude, double period) {
    double pi2 = Math.PI * 2;

    double s = period / pi2 * Math.asin(1 / amplitude);
    if ((elapsedTimeRate *= 2) < 1) {
      return (float) (-0.5f * (amplitude * Math.pow(2, 10 * (elapsedTimeRate -= 1f)) * Math.sin((elapsedTimeRate - s) * pi2 / period)));
    }
    return (float) (amplitude * Math.pow(2, -10 * (elapsedTimeRate -= 1)) * Math.sin((elapsedTimeRate - s) * pi2 / period) * 0.5 + 1);

  }
}
