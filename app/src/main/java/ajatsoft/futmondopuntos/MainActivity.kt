package ajatsoft.futmondopuntos

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.v7.widget.AppCompatButton
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var button: AppCompatButton? = null
    private lateinit var mywebview: WebView
//    private lateinit var textView: TextView
private lateinit var mInterstitialAd: InterstitialAd

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-5849370961147264~5739691624")

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-5849370961147264/3806118261"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity::class.java)
            startActivity(intent)
        }


        mywebview = findViewById<WebView>(R.id.webview) as WebView
        mywebview = findViewById<WebView>(R.id.webview) as WebView

        mywebview.settings.javaScriptEnabled = true
        mywebview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.")
                }
               // injectJavaScript(view)
              //  injectCSS()
                injectWithLoadUrlSimple(view)



            }
        }
        mywebview.visibility = View.INVISIBLE
        mywebview.loadUrl("http://www.futbolfantasy.com/laliga/puntos")

 //       Handler().postDelayed({
  //       //   findViewById<View>(R.id.fullscreen_content).visibility = (View.GONE)
  //          findViewById<View>(R.id.webview).visibility = View.VISIBLE
  //      }, 7000)


   //     fab.setOnClickListener { view ->
  //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
   //                 .setAction("Action", null).show()
   //     }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    internal fun injectWithLoadUrlSimple(view: WebView) {
        injectCSS()
        injectJavaScript(view)

        Handler().postDelayed({
        view.visibility = View.VISIBLE}, 5000)
    }

    private fun injectJavaScript(view: WebView): Boolean {
        view.loadUrl("javascript:(function() {\$('select[class=\"js_game\"]').val('futmondo-mixto').change();})()");
        return true
    }

    private fun injectCSS() {
        try {
            val inputStream = resources.openRawResource(R.raw.style)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            val encoded = Base64.encodeToString(buffer, Base64.NO_WRAP)
            mywebview.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()")

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.futmondomixto -> {
                this@MainActivity.mywebview.loadUrl(
                        "javascript:(function() {\$('select[class=\"js_game\"]')" +
                                ".val('futmondo-mixto').change();})()")
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.")
                }
            }
            R.id.picas -> {
                this@MainActivity.mywebview.loadUrl(
                        "javascript:(function() {\$('select[class=\"js_game\"]')" +
                                ".val('modo-picas').change();})()")
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.")
                }
            }
            R.id.futmondoestadisticas -> {this@MainActivity.mywebview.loadUrl(
                    "javascript:(function() {\$('select[class=\"js_game\"]')" +
                            ".val('futmondo-stats').change();})()")
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.")
                }
            }
            R.id.futmondoprensa -> {this@MainActivity.mywebview.loadUrl(
                    "javascript:(function() {\$('select[class=\"js_game\"]')" +
                            ".val('futmondo-prensa').change();})()")
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.")
                }
            }
            R.id.nav_share -> {
                shareTheLink();

            }
            R.id.nav_send -> {
                val uri = Uri.parse("mailto:ajatsoft@gmail.com")
                val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
                sendIntent.setData(uri)
                startActivity(Intent.createChooser(sendIntent, "Futmondo Puntos"))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun shareTheLink() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.share_msg_subject))
        val url = "https://play.google.com/store/apps/details?id=ajatsoft.futmondopuntos"
        share.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_msg_text) + "\n " + url)
        startActivity(Intent.createChooser(share, "Share link!"))
    }
}
