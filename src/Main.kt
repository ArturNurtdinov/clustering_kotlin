import java.awt.Dimension
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import javax.swing.JFrame
import java.util.Random

fun main(args: Array<String>) {
    if (args.isEmpty())
        error("Missing argument! \nUsage: pass file name with points to program args")

    writeListToFile(args[0], generateListOfPoints(1000000, 80))

    val frame = JFrame()
    frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
    val component = PointsComponent(retrievePointsFromFile(args[0]))
    component.preferredSize = Dimension(1200, 700)

    frame.title = "Clustering"
    frame.preferredSize = Dimension(1200, 700)
    frame.contentPane.add(component)

    frame.pack()
    frame.isVisible = true
}

private fun retrievePointsFromFile(filename: String): List<Point> =
    ObjectInputStream(FileInputStream(filename)).readObject() as List<Point>

private fun writeListToFile(filename: String, list: List<Point>) =
    ObjectOutputStream(FileOutputStream(filename)).writeObject(list)

private fun generateListOfPoints(size: Int, radius: Int): List<Point> {
    val res = mutableListOf<Point>()
    for (i in 1..size) {
        when (i) {
            in (1..size / 3) -> res.add(
                Point(((Random().nextGaussian() * radius).toInt() + PointsComponent.cluster1.x),
                    (Random().nextGaussian() * radius).toInt() + PointsComponent.cluster1.y))
            in (size / 3..size * 2 / 3) -> res.add(
                Point((Random().nextGaussian() * radius).toInt() + PointsComponent.cluster2.x,
                    (Random().nextGaussian() * radius).toInt() + PointsComponent.cluster2.y))
            in (size * 2 / 3..size) -> res.add(
                Point((Random().nextGaussian() * radius).toInt() + PointsComponent.cluster3.x,
                    (Random().nextGaussian() * radius).toInt() + PointsComponent.cluster3.y))
        }
    }

    return res
}
