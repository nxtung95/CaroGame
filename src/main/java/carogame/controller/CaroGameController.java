package carogame.controller;

import carogame.dto.KhoiTaoBanCoRequestDto;
import carogame.dto.KiemTraHopLeRequestDto;
import carogame.dto.KiemTraThangRequestDto;
import carogame.soapclient.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CaroGameController {
	@RequestMapping(value = "/khoiTao", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public KhoiTaoBanCoResponse khoiTaoBanCo(@RequestBody KhoiTaoBanCoRequestDto request, Model model) {
		KhoiTaoBanCoResponse response = new KhoiTaoBanCoResponse();
		try {

			BanCoPortService service = new BanCoPortService();
			BanCoPort banCoPort = service.getBanCoPortSoap11();
			KhoiTaoBanCoRequest khoiTaoBanCoRequest = new KhoiTaoBanCoRequest();
			khoiTaoBanCoRequest.setSoHang(request.getSoHang());
			khoiTaoBanCoRequest.setSoCot(request.getSoCot());
			response = banCoPort.khoiTaoBanCo(khoiTaoBanCoRequest);
			System.out.println("khoi tao ban co response, ketqua: " + response.isKetQua() + ", mota: " + response.getMoTa());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("ketQua", response.isKetQua());
		model.addAttribute("moTa", response.getMoTa());
		return response;
	}

	@RequestMapping(value = "/kiemTraHople", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public KiemTraTinhHopLeResponse kiemTraHople(@RequestBody KiemTraHopLeRequestDto request, Model model) {
		KiemTraTinhHopLeResponse response = new KiemTraTinhHopLeResponse();
		try {

			BanCoPortService service = new BanCoPortService();
			BanCoPort banCoPort = service.getBanCoPortSoap11();
			KiemTraTinhHopLeRequest kiemTraTinhHopLeRequest = new KiemTraTinhHopLeRequest();
			kiemTraTinhHopLeRequest.setHang(request.getHang());
			kiemTraTinhHopLeRequest.setCot(request.getCot());
			kiemTraTinhHopLeRequest.setType(request.getType());
			response = banCoPort.kiemTraTinhHopLe(kiemTraTinhHopLeRequest);
			System.out.println("Kiem tra tinh hop le response, ketqua: " + response.isKetQua() + ", mota: " + response.getMoTa());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("ketQua", response.isKetQua());
		model.addAttribute("moTa", response.getMoTa());
		return response;
	}

	@RequestMapping(value = "/kiemTraThang", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public KiemTraThangResponse kiemTraThang(@RequestBody KiemTraThangRequestDto request, Model model) {
		KiemTraThangResponse response = new KiemTraThangResponse();
		try {

			BanCoPortService service = new BanCoPortService();
			BanCoPort banCoPort = service.getBanCoPortSoap11();
			KiemTraThangRequest kiemTraThangRequest = new KiemTraThangRequest();
			kiemTraThangRequest.setHang(request.getHang());
			kiemTraThangRequest.setCot(request.getCot());
			kiemTraThangRequest.setType(request.getType());
			response = banCoPort.kiemTraThang(kiemTraThangRequest);
			System.out.println("Kiem tra thang response, ketqua: " + response.isKetQua() + ", mota: " + response.getMoTa());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("ketQua", response.isKetQua());
		model.addAttribute("moTa", response.getMoTa());
		return response;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/khoiTao", method = RequestMethod.GET)
	public String banCo() {
		return "banCo";
	}
}
