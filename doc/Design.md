# Design Notes

- A `char` in java is 16-bit, since our computer uses 16-bit words a char is used to represent most registers and memory
- The simulator typically uses the smallest datatype required for an operation to be more memory efficient
    - The simulated memory stores exactly a word size (using `char`), this reduces overhead when running the cisc simulator
- The `memory fault` and `conditon code` registers are stored as a `byte` (which is 8-bit) since they only need 4 bits
    - This could be optimized by storing both in one `byte` since each is 4-bit, using two `bytes` wastes a total of a `byte` of space
- Registers that you can't set through the GUI (panel) are marked as private
- Instructions are stored in the `Instruction` enumeration where they are defined as a `byte` datatype (since we need 6-bit instructions and byte is 8-bit)
    - Each instruction is defined as a binary literal so that the code is more readable
    - Storing in `byte` type to be memory efficient!
