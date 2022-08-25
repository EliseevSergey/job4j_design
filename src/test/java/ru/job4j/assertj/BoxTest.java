package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Sphere")
                .containsIgnoringCase("sphere")
                .startsWithIgnoringCase("s");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(666, 12);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Unknown object")
                .doesNotContain("cube");
    }

    @Test
    void cubeVertexes() {
        Box cube = new Box(8, 1);
        assertThat(cube.getNumberOfVertices())
                .isEqualTo(8)
                .isPositive();
    }

    @Test
    void unknownVertexes() {
        Box unknown = new Box(777, 1);
        assertThat(unknown.getNumberOfVertices())
                .isEqualTo(-1)
                .isNotZero()
                .isNegative();
    }

    @Test
    void notExistOne() {
        Box cube = new Box(8, -10);
        assertThat(cube.isExist()).isFalse();
    }

    @Test
    void existOne() {
        Box cube = new Box(8, 10);
        assertThat(cube.isExist()).isTrue();
    }

    @Test
    void sphereArea() {
        Box sphere = new Box(0, 10);
        assertThat(sphere.getArea())
                .isEqualTo(1256d, withPrecision(0.7d))
                .isCloseTo(1200d, Percentage.withPercentage(5));
    }

    @Test
    void tetrahedronArea() {
        Box tetrahedron = new Box(4, 9);
        assertThat(tetrahedron.getArea())
                .isEqualTo(140d, withPrecision(0.3d))
                .isGreaterThan(120d);

    }
}