// See LICENSE for license details.

package jtag

object JtagIdcode {
  /** Generates a JTAG IDCODE as a 32-bit integer, using the format in 12.1.1d.
    */
  def apply(version: Int, partNumber: Int, mfrId: Int): BigInt = {
    require(version < (1 << 4), "version field must be 4 bits at most")
    require(partNumber < (1 << 16), "part number must be 16 bits at most")
    require(mfrId < (1 << 11), "manufacturer identity must be 11 bits at most")
    BigInt(version) << 28 | BigInt(partNumber) << 12 | BigInt(mfrId) << 1 | 1
  }

  /** A dummy manufacturer ID, not to be used per 12.2.1b since bus masters may shift this out to
    * determine the end of a bus.
    */
  def dummyMfrId: Int = 0x7f
}
