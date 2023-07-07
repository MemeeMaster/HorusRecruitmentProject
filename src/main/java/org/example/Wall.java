package org.example;

import org.example.interfaces.Block;
import org.example.interfaces.CompositeBlock;
import org.example.interfaces.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<Block> extractCompositeBlock() {
        List<Block> blocksCopy = new ArrayList<>(blocks);
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                blocksCopy.addAll(((CompositeBlock) block).getBlocks());
                // blocksCopy.remove(block); // would be added if compositeBlock as whole do not count as element
            }
        }
        return blocksCopy;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return extractCompositeBlock().stream().filter(block -> Objects.equals(block.getColor(), color)).findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return extractCompositeBlock().stream().filter(block -> Objects.equals(block.getMaterial(), material)).collect(Collectors.toList());
    }

    @Override
    public int count() {
        return extractCompositeBlock().size();
    }
}