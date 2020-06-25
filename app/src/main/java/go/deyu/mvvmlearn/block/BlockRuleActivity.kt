package go.deyu.mvvmlearn.block

import android.app.AlertDialog
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import go.deyu.mvvmlearn.R
import kotlinx.android.synthetic.main.activity_block_rule.*

class BlockRuleActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_block_rule)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val create = AlertDialog.Builder(this).setView(R.layout.dialog_add_block).create()
//            create.findViewById(R.id.)
        }
        NavigationUI.setupWithNavController(bv,(nav_host_fragment as NavHostFragment).navController)
    }
}