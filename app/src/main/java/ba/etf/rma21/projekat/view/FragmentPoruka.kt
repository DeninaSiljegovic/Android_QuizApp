package ba.etf.rma21.projekat.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.R

class FragmentPoruka : Fragment() {
    private lateinit var poruka: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.poruka_fragment, container, false)

        val grupa = arguments?.getString("grupa")
        val predmet = arguments?.getString("predmet")

        poruka = view.findViewById(R.id.tvPoruka)

        poruka.text = "Uspješno ste upisani u grupu $grupa predmeta $predmet!"

        return view
    }


    companion object {
        fun newInstance(): FragmentPoruka = FragmentPoruka()
    }
}