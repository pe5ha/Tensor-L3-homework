import java.util.ArrayList

interface FileSystemNode {
    val name: String
    val path: String
    var parent: FileSystemNode?
}

open class AbstractFileSystemNode(
    override val name: String,
    override var parent: FileSystemNode?
) :
    FileSystemNode {

    override val path: String
        get() = parent?.let { "${it.path}/$name" } ?: name

}

class Folder(
    name: String,
    parent: FileSystemNode?,
    vararg _childElements: FileSystemNode
) :
    AbstractFileSystemNode(name, parent) {

    val childElements: ArrayList<FileSystemNode> =
        _childElements.mapTo(ArrayList()){
            it.parent = this
            it
        }

    init {
        if (parent is Folder) parent.addChild(this)
    }

    fun addChild(child: FileSystemNode) {
        childElements.add(child)
    }

    fun textView(i: Int): String { // i - количество табов
        var i = i
        var view = ""
        for (j in 0 until i) view += "\t"
        view += "/ $name\n"
        i++
        for (sn in childElements) {
            if (sn is File) {
                for (j in 0 until i) view += "\t"
                view += """
                    ${sn.name}
                    
                    """.trimIndent()
            } else if (sn is Folder) {
                view += sn.textView(i)
            }
        }
        return view
    }

    override fun toString(): String {
        return textView(0)
    }

}

class File(
    name: String,
    parent: FileSystemNode?
) : AbstractFileSystemNode(name, parent) {
    init {
        if (parent is Folder) parent.addChild(this)
    }

    val extension: String
        get() = name.split("\\.".toRegex()).toTypedArray()[name.split("\\.".toRegex()).toTypedArray().size - 1]

}