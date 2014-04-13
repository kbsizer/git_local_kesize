package ken.euler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Problems1To99 {

	@Test
	public void p01_MultiplesOf3And5() {
		int sum = 0;
		for (int i = 1; i < 1000; i++) {
			if ((i % 3 == 0) || (i % 5 == 0)) {
				sum += i;
			}
		}

		assertEquals("Answer", 233168, sum);
	}

	@Test
	public void p02_EvenFibonacciNumbers() {
		Fibonacci f = new Fibonacci();
		int sum = 0;
		int nextFib = f.next();
		while (nextFib <= 4000000) {
			if (nextFib % 2 == 0) {
				sum += nextFib;
			}
			nextFib = f.next();
		}

		assertEquals("Answer", 4613732, sum);
	}

	static class Fibonacci {
		private int f0 = 1;
		private int f1 = 2;

		public int next() {
			int retVal = f0;
			int f2 = f0 + f1;
			f0 = f1;
			f1 = f2;
			return retVal;
		}
	}

	@Test
	public void p03_LargestPrimeFactor() {
		long num = 600851475143L;
		long largestFactor = 0;
		PrimeFactory primes = new PrimeFactory();
		while (num > 1) {
			long nextPrime = primes.next();
			while (num % nextPrime == 0) {
				num /= nextPrime;
				largestFactor = nextPrime;
			}
		}

		assertEquals("Answer", 6857, largestFactor);

	}

	static class PrimeFactory {
		private List<Long> primes = new ArrayList<Long>();

		long next() {
			if (primes.size() == 0) {
				primes.add(2L);
				return 2L;
			}

			boolean isPrime;
			long candidate = primes.get(primes.size() - 1);
			do {
				candidate += 1;
				isPrime = true;
				for (Long p : primes) {
					if (candidate % p == 0) {
						isPrime = false;
						break;
					}
				}
			} while (isPrime == false);

			primes.add(candidate);
			return candidate;
		}
	}

	/**
	 * Find the largest palindrome made from the product of two 3-digit numbers.
	 */
	@Test
	public void p04_LargestPalindromeProduct() {
		int largestPalindrome = 0;
		for (int a = 999; a >= 100; a--) {
			for (int b = 999; b >= 100; b--) {
				int product = a * b;
				if (product > largestPalindrome && isPalindrome(product)) {
					largestPalindrome = product;
				}
			}
		}

		assertEquals("Answer", 906609, largestPalindrome);
	}

	private boolean isPalindrome(int n) {
		char[] nStr = Integer.toString(n).toCharArray();
		int first = 0;
		int last = nStr.length - 1;
		boolean palindromeFound = true;
		while (first <= last && palindromeFound) {
			if (nStr[first] != nStr[last]) {
				palindromeFound = false;
			}
			first++;
			last--;
		}
		return palindromeFound;
	}

	/**
	 * 2520 is the smallest number that can be divided by each of the numbers
	 * from 1 to 10 without any remainder.
	 * <p>
	 * What is the smallest positive number that is evenly divisible by all of
	 * the numbers from 1 to 20?
	 */
	@Test
	public void p05_SmallestMultiple() {
		int smallestMultiple = 2520; // may as well start here (from problem
										// text above)
		while (canBeDividedByAll(smallestMultiple) == false) {
			smallestMultiple++;
		}

		assertEquals("Answer", 232792560, smallestMultiple);
	}

	private boolean canBeDividedByAll(int n) {
		// note: we could be clever and only look at the primes
		boolean candidateFound = true;
		for (int i = 2; i <= 20; i++) {
			if (n % i != 0) {
				candidateFound = false;
				break;
			}
		}
		return candidateFound;
	}

	/**
	 * The sum of the squares of the first ten natural numbers is,
	 * <p>
	 * 1^2 + 2^2 + ... + 10^2 = 385
	 * <p>
	 * The square of the sum of the first ten natural numbers is,
	 * <p>
	 * (1 + 2 + ... + 10)^2 = 55^2 = 3025
	 * <p>
	 * Hence the difference between the sum of the squares of the first ten
	 * natural numbers and the square of the sum is 3025 - 385 = 2640.
	 * <p>
	 * Find the difference between the sum of the squares of the first one
	 * hundred natural numbers and the square of the sum.
	 */
	@Test
	public void p06_SumSquareDifference() {
		long sumOfSquares = 0;
		long sum = 0;
		for (long i = 1; i <= 100; i++) {
			sum += i;
			sumOfSquares += i * i;
		}
		long diff = (sum * sum) - sumOfSquares;

		assertEquals("Answer", 25164150, diff);
	}

	/**
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can
	 * see that the 6th prime is 13.
	 * <p>
	 * What is the 10,001st prime number?
	 */
	@Test
	public void p07_10001stPrime() {
		long prime = 1L;
		;
		PrimeFactory primes = new PrimeFactory();
		for (int i = 1; i <= 10001; i++) {
			prime = primes.next();
		}

		assertEquals("Answer", 104743, prime);
	}

	/**
	 * Largest product in a series
	 * <p>
	 * Find the greatest product of five consecutive digits in the 1000-digit
	 * number.
	 * 
	 * 73167176531330624919225119674426574742355349194934
	 * 96983520312774506326239578318016984801869478851843
	 * 85861560789112949495459501737958331952853208805511
	 * 12540698747158523863050715693290963295227443043557
	 * 66896648950445244523161731856403098711121722383113
	 * 62229893423380308135336276614282806444486645238749
	 * 30358907296290491560440772390713810515859307960866
	 * 70172427121883998797908792274921901699720888093776
	 * 65727333001053367881220235421809751254540594752243
	 * 52584907711670556013604839586446706324415722155397
	 * 53697817977846174064955149290862569321978468622482
	 * 83972241375657056057490261407972968652414535100474
	 * 82166370484403199890008895243450658541227588666881
	 * 16427171479924442928230863465674813919123162824586
	 * 17866458359124566529476545682848912883142607690042
	 * 24219022671055626321111109370544217506941658960408
	 * 07198403850962455444362981230987879927244284909188
	 * 84580156166097919133875499200524063689912560717606
	 * 05886116467109405077541002256983155200055935729725
	 * 71636269561882670428252483600823257530420752963450
	 * <p>
	 * Note: If the nth digit is a zero, then we can skip ahead 5 digits.
	 */
	@Test
	public void p08_LargestProductInSeries() {
		String s = "73167176531330624919225119674426574742355349194934"
				+ "96983520312774506326239578318016984801869478851843"
				+ "85861560789112949495459501737958331952853208805511"
				+ "12540698747158523863050715693290963295227443043557"
				+ "66896648950445244523161731856403098711121722383113"
				+ "62229893423380308135336276614282806444486645238749"
				+ "30358907296290491560440772390713810515859307960866"
				+ "70172427121883998797908792274921901699720888093776"
				+ "65727333001053367881220235421809751254540594752243"
				+ "52584907711670556013604839586446706324415722155397"
				+ "53697817977846174064955149290862569321978468622482"
				+ "83972241375657056057490261407972968652414535100474"
				+ "82166370484403199890008895243450658541227588666881"
				+ "16427171479924442928230863465674813919123162824586"
				+ "17866458359124566529476545682848912883142607690042"
				+ "24219022671055626321111109370544217506941658960408"
				+ "07198403850962455444362981230987879927244284909188"
				+ "84580156166097919133875499200524063689912560717606"
				+ "05886116467109405077541002256983155200055935729725"
				+ "71636269561882670428252483600823257530420752963450";

		int largestProduct = 1;
		int ndx = 0;
		int last = s.length() - 5;
		while (ndx <= last) {
			int d1 = s.charAt(ndx) & 0x0F;
			int d2 = s.charAt(ndx + 1) & 0x0F;
			int d3 = s.charAt(ndx + 2) & 0x0F;
			int d4 = s.charAt(ndx + 3) & 0x0F;
			int d5 = s.charAt(ndx + 4) & 0x0F;
			int product = d1 * d2 * d3 * d4 * d5;
			if (product > largestProduct) {
				largestProduct = product;
			} else if (product == 0) {
				int ndxOfZero = s.indexOf('0', ndx);
				ndx = ndxOfZero;
			}
			ndx++;
		}

		assertEquals("Answer", 40824, largestProduct);
	}

	/**
	 * Special Pythagorean Triplet
	 * <p>
	 * A Pythagorean Triplet is a set of three natural numbers, a < b < c, for
	 * which,
	 * <p>
	 * a^2 + b^2 = c^2 
	 * <p>
	 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
	 * <p>
	 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
	 * Find the product abc.
	 */
	@Test
	public void p09_SpecialPythagoreanTriplet() {
		int answer = 0;
		for ( int c=1000/3;c<1000;c++) {
			for (int b=2;b<c && 1000-b-c > 0;b++) {
				int a = 1000-b-c;
				if ( a*a + b*b == c*c && a < b) {
					answer = a*b*c;
				}
			}
		}
		assertEquals("Answer", 31875000, answer);
	}
}
