package zone.bokmark.espressokit

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedDiagnosingMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import java.util.Locale


/**
 * 检查元素的文本
 */
fun ViewInteraction.checkText(text: String) {
    check(ViewAssertions.matches(ViewMatchers.withText(text)))
}

/**
 * 检查文本大小
 */
fun ViewInteraction.checkTextSize(sp: Float) {
    check(ViewAssertions.matches(withTextSize(sp)))
}

/**
 * 检查是否包含了某些了文本
 */
fun ViewInteraction.containsText(text: String) {
    check(ViewAssertions.matches(ViewMatchers.withSubstring(text)))
}

/**
 * 检查元素的文本颜色
 */
fun ViewInteraction.checkTextColor(colorHex: String) {
    check(ViewAssertions.matches(withTextColor(colorHex)))
}

private fun withTextColor(colorHex: String) = object : BoundedDiagnosingMatcher<View, TextView>(TextView::class.java) {
    override fun matchesSafely(item: TextView, mismatchDescription: Description): Boolean {
        val textViewColor: Int = item.currentTextColor
        val expectedColor: Int = colorHex.toColorInt()

        mismatchDescription
            .appendText("textView.getCurrentTextColor() was ")
            .appendText(getColorHex(textViewColor))
        return textViewColor == expectedColor
    }

    override fun describeMoreTo(description: Description) {
        description.appendText("textView.getCurrentTextColor() is color with value $colorHex")
    }

    private fun getColorHex(color: Int): String {
        return String.format(
            Locale.ROOT, "#%02X%06X", 0xFF and Color.alpha(color), 0xFFFFFF and color
        )
    }
}

private fun withTextSize(expectedTextSize: Float) =
    object : BoundedDiagnosingMatcher<View, TextView>(TextView::class.java) {
        override fun matchesSafely(item: TextView, mismatchDescription: Description): Boolean {
            val textViewColor: Float = item.textSize

            mismatchDescription
                .appendText("textView.textSize was ")
                .appendText(textViewColor.toString())
            return textViewColor == expectedTextSize
        }

        override fun describeMoreTo(description: Description) {
            description.appendText("textView.textSize is $expectedTextSize")
        }
    }
