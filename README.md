# Kata-Layouter
Requirements
 * 1. Place List of Blocks on one Page
 * 2. Blocks are placed in two columns of similar height starting from top. Last column must not be longer than previous column. Reading sequence must not be changed.
 * 3. Blocks may have different integer heights. Blocks can be divided into parts. Each part must have an integer height.
 * 4. The first block might be of type headline. Headline blocks may span multiple columns. All other blocks span just one column
 
To be implemented
 * 5. Pages have a maximum height and a maximum number of columns. Nevertheless two columns should be used whenever possible.
 * 6. Multiple pages may be added. Each page except the last one must be filled as much as possible.
 * 7. In any column, the total height of non-headline blocks must not be less than 5
 * 8. Blocks may be of type Picture. Pictures must not be divided into parts.
 * 9. If a picture needs to be placed in a new column due to its height, regular text blocks shall be placed before to fill the current column.
 * 10. Blocks may be of type Picture Caption. If a Picture Caption is directly below a Picture, it must be placed always directly below the picture inside the same column.
 * 11. One Picture may span more than one column. In this case, the picture shall always be placed at the bottom right corner of the final placement.
 
