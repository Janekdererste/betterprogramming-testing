package com.janeklaudan.betterProgramming.firstExample;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbstractEntityTest {

    @Test
    public void equals_equalIds_true() {

        final String id = "some-id";
        final TestableEntity entity = new TestableEntity(id);
        final TestableEntity other = new TestableEntity(id);

        boolean result = entity.equals(other);

        assertTrue(result);
    }

    @Test
    public void equals_idsNotEqual_false() {

        final TestableEntity entity = new TestableEntity("some-id");
        final TestableEntity other = new TestableEntity("other-id");

        boolean result = entity.equals(other);

        assertFalse(result);
    }

    @Test
    public void equals_otherIsNull_false() {

        final TestableEntity entity = new TestableEntity("some-id");

        boolean result = entity.equals(null);

        assertFalse(result);
    }

    @Test
    public void equals_sameReference_true() {

        final TestableEntity entity = new TestableEntity("some-id");

        boolean result = entity.equals(entity);

        assertTrue(result);
    }

    @Test
    public void equals_idsAreNullSameReference_true() {

        final TestableEntity entity = new TestableEntity(null);

        boolean result = entity.equals(entity);

        assertTrue(result);
    }

    @Test
    public void equals_idsAreNullDifferentReference_false() {

        final TestableEntity entity = new TestableEntity(null);
        final TestableEntity other = new TestableEntity(null);

        boolean result = entity.equals(other);

        assertFalse(result);
    }

    @Test
    public void equals_otherIdIsNull_false() {
        final TestableEntity entity = new TestableEntity("some-id");
        final TestableEntity other = new TestableEntity(null);

        boolean result = entity.equals(other);

        assertFalse(result);
    }

    @Test
    public void equals_ownIdIsNull_false() {

        final TestableEntity entity = new TestableEntity(null);
        final TestableEntity other = new TestableEntity("some-id");

        boolean result = entity.equals(other);

        assertFalse(result);
    }

    @Test
    public void equals_otherObjectIsAnotherType_false() {

        final TestableEntity entity = new TestableEntity("some-id");

        boolean result = entity.equals(new Object());

        assertFalse(result);
    }

    private static class TestableEntity extends AbstractEntity {

        TestableEntity(String id) {
            setId(id);
        }
    }
}
