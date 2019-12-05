import java.awt.Color
import java.awt.Graphics
import javax.swing.JComponent
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

class PointsComponent(private val points: List<Point>) : JComponent() {
    companion object {
        val cluster1 = Point(100, Random.nextInt(700))
        val cluster2 = Point(600, Random.nextInt(700))
        val cluster3 = Point(1050, Random.nextInt(700))
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        points.forEach {
            with(it) {
                if (getDistance(it, cluster1) < getDistance(it, cluster2)) {
                    g?.color = Color.BLUE
                } else if ((getDistance(it, cluster2) < getDistance(it, cluster1)) && (getDistance(it, cluster2) < getDistance(it, cluster3))) {
                    g?.color = Color.RED
                } else if (getDistance(it, cluster3) < getDistance(it, cluster2)) {
                    g?.color = Color.GREEN
                }
                g?.fillOval(x, y, 2, 2)
            }
        }
    }

    private fun getDistance(p1: Point, p2: Point): Double =
        sqrt((p2.x - p1.x).toDouble().pow(2) + (p2.y - p1.y).toDouble().pow(2))
}