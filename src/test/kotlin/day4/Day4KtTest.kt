package day4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day4KtTest {

    @Test
    fun part1() {
        val passports = parsePassports(input)
        val validCount = passports.count { it.isValid() }
        assertEquals(2, validCount)
    }

    @Test
    fun part2() {
        val passports = parsePassports(input)
        val validCount = passports.count { it.isValidPart2() }
        assertEquals(2, validCount)
    }

    @Test
    fun byrValid() {
        assertTrue("1920".isbyrValid())
        assertFalse("1920e".isbyrValid())
    }

    @Test
    fun hgtValid() {
        assertTrue("183cm".ishgtValid())
        assertTrue("60in".ishgtValid())
        assertFalse("2000cm".isbyrValid())
        assertFalse("29in".isbyrValid())
        assertFalse("105".isbyrValid())
    }

    @Test
    fun hclValid() {
        assertTrue("#09cdda".ishclValid())
        assertFalse("#09cddaa".ishclValid())
        assertFalse("#09cddk".ishclValid())
    }

    @Test
    fun eclValid() {
        assertTrue("blu".iseclValid())
        assertFalse("hej".iseclValid())

    }

    @Test
    fun pidValid() {
        assertTrue("000000001".ispidValid())
        assertFalse("0123456789".ispidValid())

    }
}

private val input = """ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in"""