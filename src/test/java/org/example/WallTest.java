package org.example;

import org.example.interfaces.Block;
import org.example.interfaces.CompositeBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class WallTest {
    private Wall wall;

    private static class TestBlock implements Block {
        private final String color;
        private final String material;

        public TestBlock(String color, String material) {
            this.color = color;
            this.material = material;
        }

        @Override
        public String getColor() {
            return this.color;
        }

        @Override
        public String getMaterial() {
            return this.material;
        }
    }

    private static class TestCompositeBlock implements CompositeBlock {
        private final String color;
        private final String material;
        private final List<Block> blocks;

        // the color and material of the composite block is a mixture of the properties of its components separated by "/". For example CompositeBlock created from wooden and plastic block would have material: "wood/plastic"

        public TestCompositeBlock(List<Block> blocks) {
            this.blocks = blocks;

            List<String> colors = blocks.stream().map(Block::getColor).distinct().toList();
            this.color = String.join("/", colors);

            List<String> materials = blocks.stream().map(Block::getMaterial).distinct().toList();
            this.material = String.join("/", materials);
        }

        @Override
        public String getColor() {
            return this.color;
        }

        @Override
        public String getMaterial() {
            return this.material;
        }

        @Override
        public List<Block> getBlocks() {
            return this.blocks;
        }
    }

    @BeforeEach
    public void blocks_initialization() {
        TestBlock redBlock = new TestBlock("red", "stone");
        TestBlock blueBlock = new TestBlock("blue", "wood");
        TestBlock greenBlock = new TestBlock("green", "plastic");
        TestCompositeBlock compositeBlock = new TestCompositeBlock(List.of(blueBlock, greenBlock));

        List<Block> wallElements = List.of(redBlock, compositeBlock);

        wall = new Wall(wallElements);
    }

    @Test
    @DisplayName("Should extract blocks from CompositeBlock correctly")
    public void test_composite_block_extracting() {
        List<Block> result = wall.extractCompositeBlock(); // the answer should be 4 because I take as an element "Block", "ComposeBlock" as a whole and its two elements
        assertEquals(result.size(), 4);             // make it 3 if CompositeBlock as whole do not count as element
    }

    @ParameterizedTest
    @DisplayName("findBlockByColor: BLOCK/COMPOSITE BLOCK/COMPOSITE BLOCK COMPONENT/COLOR NOT FOUND")
    @CsvSource({"red, red", "blue/green, blue/green", "green, green", "orange, ''"}) // if CompositeBlock as whole do not count as element it is required to remove
    public void test_finding_by_color(String searchColor, String expectedColor) {    // "blue/green, blue/green" parameter for it to work
        Optional<Block> result = wall.findBlockByColor(searchColor);

        if (expectedColor.isEmpty()) {
            assertFalse(result.isPresent());
        } else {
            assertTrue(result.isPresent());
            assertEquals(expectedColor, result.get().getColor());
        }
    }

    @ParameterizedTest
    @DisplayName("findBlocksByMaterial: BLOCK/COMPOSITE BLOCK/COMPOSITE BLOCK COMPONENT/MATERIAL NOT FOUND")
    @CsvSource({"stone, stone", "wood/plastic, wood/plastic", "wood, wood", "concrete, ''"}) // if CompositeBlock as whole do not count as element it is required to remove
    public void test_finding_by_material(String searchMaterial, String expectedMaterial) {   // "wood/plastic, wood/plastic" parameter for it to work
        List<Block> result = wall.findBlocksByMaterial(searchMaterial);

        if (expectedMaterial.isEmpty()) {
            assertTrue(result.isEmpty());
        } else {
            assertFalse(result.isEmpty());
            assertEquals(result.get(0).getMaterial(), expectedMaterial);
        }
    }

    @Test
    @DisplayName("Should count properly")
    public void test_counting() { // the answer should be 4 because I take as an element "Block", "ComposeBlock" as a whole and its two elements
        assertEquals(wall.count(), 4); // make it 3 if CompositeBlock as whole do not count as element
    }
}
